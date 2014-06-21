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

package net.openhft.fix.include.util;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import net.openhft.fix.include.v42.FIXMessageBuilder;
import net.openhft.fix.include.v42.FixMessage;
import net.openhft.lang.io.NativeBytes;

public class FixMessagePool implements FixPoolFactory<FixMessage>{
	
	private FixMessageContainer<FixMessage>[] fixMessageArr;	
	private volatile int objGetPosition;
	private int objectPutPosition;	
	private final int mask;
	private final long BASE_ADDR;
	private final long ARR_INDEX;
	private final long TAIL_ADJUSTMENT;
	
	private ThreadLocal<FixMessageContainer<FixMessage>> fixLocal = new ThreadLocal<>();
	
	@SuppressWarnings({ "unchecked", "restriction" })
	public FixMessagePool(FixPoolFactory<FixMessage> fixPoolFactory , int poolSize){
		
		int currentSize=1;
		while(currentSize<poolSize){
			currentSize = currentSize << 1;
		}
		poolSize = currentSize;
		fixMessageArr = new FixMessageContainer[poolSize];
		for(int x=0;x<poolSize;x++)
		{
			fixMessageArr[x] = new FixMessageContainer<FixMessage>(fixPoolFactory.create());
		}
		mask = poolSize-1;
		objectPutPosition = poolSize;
		BASE_ADDR = NativeBytes.UNSAFE.arrayBaseOffset(FixMessageContainer[].class);
		ARR_INDEX = NativeBytes.UNSAFE.arrayIndexScale(FixMessageContainer[].class);
		TAIL_ADJUSTMENT = 31 - Integer.numberOfLeadingZeros((int) ARR_INDEX);
	}
	
	@SuppressWarnings("restriction")
	public FixMessageContainer<FixMessage> getFixMessage(){
		int localTakePointer;
		
		FixMessageContainer<FixMessage> localObject = fixLocal.get();
		if(localObject!=null){
			if(localObject.state.compareAndSet(FixMessageContainer.AVAILABLE_STATE, FixMessageContainer.IN_USE_STATE)){
				return localObject;
			}
		}
		
		while(objectPutPosition != (localTakePointer=objGetPosition) ){
			int index = localTakePointer & mask;
			FixMessageContainer<FixMessage> fixMsgContainer = fixMessageArr[index];			
			if(fixMsgContainer!=null && NativeBytes.UNSAFE.compareAndSwapObject(fixMessageArr, (index<<TAIL_ADJUSTMENT)+BASE_ADDR, fixMsgContainer, null)){
				objGetPosition = localTakePointer+1;				
				if(fixMsgContainer.state.compareAndSet(FixMessageContainer.AVAILABLE_STATE, FixMessageContainer.IN_USE_STATE)){
					fixLocal.set(fixMsgContainer);
					return fixMsgContainer;
				}
			}
		}
		return null;		
	}
	
	@SuppressWarnings("restriction")
	public void putFixMessage(FixMessageContainer<FixMessage> fixMessage) throws Exception{
			int localPosition=objectPutPosition;			
			long index = ((localPosition & mask)<<TAIL_ADJUSTMENT ) + BASE_ADDR;
			if(fixMessage.state.compareAndSet(FixMessageContainer.AVAILABLE_STATE, FixMessageContainer.IN_USE_STATE)){
				NativeBytes.UNSAFE.putOrderedObject(fixMessageArr, index, fixMessage);
				objectPutPosition = localPosition+1;
			}
			else{
				throw new Exception("Not a valid position address");
			}		
	}
	
	@SuppressWarnings("hiding")
	public static class FixMessageContainer<FixMessage>{
		private FixMessage fixMessageContent;
		public static final int AVAILABLE_STATE=0;
		public static final int IN_USE_STATE=1;
		
		private AtomicInteger state = new AtomicInteger(AVAILABLE_STATE);
		public FixMessageContainer(FixMessage fixMessageContent){
			this.fixMessageContent = fixMessageContent;
		}
		
		public FixMessage getFixMessageContent() {
			return fixMessageContent;
		}
	}

	@Override
	public FixMessage create() {
		FixMessage fm = new FIXMessageBuilder().createFixMessage(true);
		return fm;
	}

	 
}
