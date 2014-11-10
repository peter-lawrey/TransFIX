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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Messages implements FixMessageType
{
    protected HugeArray<Message> messageArr;
    protected int messageSize;   
    protected int fieldSize;
    protected int groupSize;
    
    
    @SuppressWarnings("unchecked")
	public Messages setMessagesSize(int messageSize){
    	this.messageSize=messageSize;
    	return this;
    }
    
    @SuppressWarnings("unchecked")
	public Messages setFieldSize(int fieldSize){this.fieldSize=fieldSize;return this;}
    
    @SuppressWarnings("unchecked")
	public Messages setGroupSize(int groupSize){this.groupSize=groupSize;return this;}
        
    @SuppressWarnings("unchecked")
	public HugeArray<Message> getMessage() {
        if (messageArr == null) {
            messageArr = HugeCollections.newArray(Message.class, messageSize);
            for (int i=0;i<messageSize;i++)
            {
            	Message message = messageArr.get(i);
            	message.setFieldSize(fieldSize);
            	message.setGroupSize(groupSize);
            }
        }
        return this.messageArr;
    }

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
	
	    out.writeObject(messageArr);
	    out.writeInt(messageSize);
	    out.writeInt(fieldSize);
	    out.writeInt(groupSize);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		messageArr= (HugeArray<Message>) in.readObject();
		messageSize= in.readInt();
		fieldSize= in.readInt();
		groupSize = in.readInt();
		
	}

	@Override
	public <T> T getField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T setValueSize(int valueSize) {
		// TODO Auto-generated method stub
		return null;
	}



}
