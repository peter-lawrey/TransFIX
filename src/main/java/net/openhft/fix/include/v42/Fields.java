/*
 * Copyright 2013 Peter Lawrey
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
package net.openhft.fix.include.v42;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Encapsulates all FIX fields in the form of an array. Each array element represents an individual FIX field.
 * Currently a placeholder
 */
public class Fields implements FixMessageType {
    protected HugeArray<Field> fieldArr;
    protected int fieldSize = 446;//fix42 has 446 fields
    protected int valueSize;

    /* (non-Javadoc)
  * @see net.openhft.fix.include.v42.FixMessageType#setFieldSize(int)
  */
    @SuppressWarnings("unchecked")
    @Override
    public Fields setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
        return this;
    }

    /* (non-Javadoc)
  * @see net.openhft.fix.include.v42.FixMessageType#setValueSize(int)
  */
    @SuppressWarnings("unchecked")
    @Override
    public Fields setValueSize(int valueSize) {
        this.valueSize = valueSize;
        return this;
    }

    /* (non-Javadoc)
  * @see net.openhft.fix.include.v42.FixMessageType#getField()
  */
    @SuppressWarnings("unchecked")
    @Override
    public HugeArray<Field> getField() {
        if (fieldArr == null) {
            fieldArr = HugeCollections.newArray(Field.class, fieldSize);
            for (int i = 0; i < fieldSize; i++) {
                Field field = fieldArr.get(i);
                field.setValueSize(valueSize);
            }
        }
        return this.fieldArr;
    }

    /* (non-Javadoc)
     * @see net.openhft.fix.include.v42.FixMessageType#getMessage()
     */
    @Override
    public <T> T getMessage() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see net.openhft.fix.include.v42.FixMessageType#setMessagesSize(int)
     */
    @Override
    public <T> T setMessagesSize(int messageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see net.openhft.fix.include.v42.FixMessageType#setGroupSize(int)
     */
    @Override
    public <T> T setGroupSize(int groupSize) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(fieldArr);
        out.writeInt(fieldSize);
        out.writeInt(valueSize);
    }

    /* (non-Javadoc)
     * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        fieldArr = (HugeArray<Field>) in.readObject();
        fieldSize = in.readInt();
        valueSize = in.readInt();
    }

}
