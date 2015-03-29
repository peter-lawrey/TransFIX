/*
 * Copyright 2013 peter.lawrey Lawrey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.fix.include.util;

import net.openhft.fix.include.v42.FixMessage;
import net.openhft.lang.io.NativeBytes;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Off-heap, lock-free FixMessage object pool. All checkouts from the pool are ThreadLocal-based
 * and can be obtained even before the threadlocal releases it.
 */
public class FixMessagePool implements FixPoolFactory<FixMessage> {

    private FixMessageContainer[] fixMessageArr;
    private volatile int objGetPosition;
    private int objectPutPosition;
    private final int mask;
    private final long BASE_ADDR;
    private final long ARR_INDEX;
    private final long TAIL_ADJUSTMENT;
    private FixConfig fixConfig = new FixConfig();

    private ThreadLocal<FixMessageContainer> fixLocal = new ThreadLocal<FixMessageContainer>();


    /**
     * Constructor to create FixMessage objects and initialize the pool with default FixConfig.
     * fixPoolFactory should be null for implementation of FixPoolFactory as this class.
     *
     * @param fixPoolFactory -factory used for create a new FixMessage Object
     * @param poolSize       -usually, should equal to the number of processors on the machine
     * @param useDefault     -instructs to create from FixConfig. Currently, only default=true
     *                       implemented.
     */
    @SuppressWarnings({"unchecked", "restriction"})
    public FixMessagePool(FixPoolFactory<FixMessage> fixPoolFactory, int poolSize,
                          boolean useDefault) {

        if (fixPoolFactory == null) {
            fixPoolFactory = this;
        }
        int currentSize = 1;
        while (currentSize < poolSize) {
            currentSize = currentSize << 1;
        }
        poolSize = currentSize;
        fixMessageArr = new FixMessageContainer[poolSize];
        for (int i = 0; i < poolSize; i++) {
            fixMessageArr[i] = new FixMessageContainer(fixPoolFactory.create(useDefault));
        }
        mask = poolSize - 1;
        objectPutPosition = poolSize;
        BASE_ADDR = NativeBytes.UNSAFE.arrayBaseOffset(FixMessageContainer[].class);
        ARR_INDEX = NativeBytes.UNSAFE.arrayIndexScale(FixMessageContainer[].class);
        TAIL_ADJUSTMENT = 31 - Integer.numberOfLeadingZeros((int) ARR_INDEX);
    }

    /**
     * Checks out an unused FixMessage object from the pool. The FixMessage object needs to be
     * obtained from its FixMessageContainer
     *
     * @return FixMessageContainer
     */
    @SuppressWarnings("restriction")
    public FixMessageContainer getFixMessageContainer() {
        int localTakePointer;

        FixMessageContainer localObject = fixLocal.get();
        if (localObject != null) {
            if (localObject.state.compareAndSet(FixMessageContainer.AVAILABLE_STATE,
                    FixMessageContainer.IN_USE_STATE)) {
                return localObject;
            }
        }

        while (objectPutPosition != (localTakePointer = objGetPosition)) {
            int index = localTakePointer & mask;
            FixMessageContainer fixMsgContainer = fixMessageArr[index];
            if (fixMsgContainer != null && NativeBytes.UNSAFE.compareAndSwapObject(
                    fixMessageArr, (index << TAIL_ADJUSTMENT) + BASE_ADDR, fixMsgContainer, null)) {
                objGetPosition = localTakePointer + 1;
                if (fixMsgContainer.state.compareAndSet(FixMessageContainer.AVAILABLE_STATE,
                        FixMessageContainer.IN_USE_STATE)) {
                    fixLocal.set(fixMsgContainer);
                    return fixMsgContainer;
                }
            }
        }
        return null;
    }

    /**
     * @param fixMsgContainer -checks in this FixMessage object from FixMessageContainer.
     * @throws Exception- For invalid position address
     */
    @SuppressWarnings("restriction")
    public void putFixMessageContainer(FixMessageContainer fixMsgContainer) throws Exception {
        int localPosition = objectPutPosition;
        long index = ((localPosition & mask) << TAIL_ADJUSTMENT) + BASE_ADDR;
        if (fixMsgContainer.state.compareAndSet(
                FixMessageContainer.IN_USE_STATE, FixMessageContainer.AVAILABLE_STATE)) {
            NativeBytes.UNSAFE.putOrderedObject(fixMessageArr, index, fixMsgContainer);
            objectPutPosition = localPosition + 1;
        } else {
            throw new Exception("Not a valid position address");
        }
    }

    /**
     * Static Inner class used as a container for FixMessage objects. This is used for maintaining
     * the state of the FixMessage Object's state. The state of a FixMessage object can be either
     * in-use OR available.
     */
    @SuppressWarnings("hiding")
    public static class FixMessageContainer implements Externalizable {
        private FixMessage fixMessage;
        public static final int AVAILABLE_STATE = 0;
        public static final int IN_USE_STATE = 1;

        private AtomicInteger state = new AtomicInteger(AVAILABLE_STATE);

        public FixMessageContainer(FixMessage fixMessage) {
            this.fixMessage = fixMessage;
        }

        /**
         * @return FixMessage
         */
        public FixMessage getFixMessage() {
            return fixMessage;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(fixMessage);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void readExternal(ObjectInput in) throws IOException,
                ClassNotFoundException {
            this.fixMessage = (FixMessage) in.readObject();
        }
    }

    /* (non-Javadoc)
     * @see net.openhft.fix.include.util.FixPoolFactory#create(boolean)
     */
    @Override
    public FixMessage create(boolean useDefault) {
        @SuppressWarnings("static-access")
        FixMessage fm = new FixMessage(fixConfig.SERVER_DEFAULT_4_2.clone()
                .setFixVersionMajor(4)
                .setFixVersionMinor(2)
                .setFixVersionServicePack(0)
                .createServerFixFields().getFieldArr());
        return fm;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeObject(fixMessageArr);
        out.writeInt(objGetPosition);
        out.writeInt(objectPutPosition);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {

        this.fixMessageArr = (FixMessageContainer[]) in.readObject();
        this.objGetPosition = in.readInt();
        this.objectPutPosition = in.readInt();


    }


}
