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

public class Fields implements FixMessageType
{
   protected HugeArray<Field> fieldArr;
   protected int fieldSize=446;//fix42 has 446 fields
   protected int valueSize;
        
   @SuppressWarnings("unchecked")
   @Override
   public Fields setFieldSize(int fieldSize){this.fieldSize=fieldSize;return this;}
   
   @SuppressWarnings("unchecked")
   @Override
   public Fields setValueSize(int valueSize){this.valueSize=valueSize;return this;}
   
   @SuppressWarnings("unchecked")
   @Override
   public HugeArray<Field> getField() {
        if (fieldArr == null) {
            fieldArr = HugeCollections.newArray(Field.class, fieldSize);
            for (int i=0;i<fieldSize;i++)
            {
            	Field field = fieldArr.get(i);
            	field.setValueSize(valueSize);
            }
        }
        return this.fieldArr;
    }

	@Override
	public <T> T getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <T> T setMessagesSize(int messageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <T> T setGroupSize(int groupSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(fieldArr);
		out.writeInt(fieldSize);
		out.writeInt(valueSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		fieldArr = (HugeArray<Field>) in.readObject();
		fieldSize = in.readInt();
		valueSize = in.readInt();
	}
	
}
