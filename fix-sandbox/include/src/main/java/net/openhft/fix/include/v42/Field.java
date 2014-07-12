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
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import net.openhft.fix.model.FixFieldTypeInterface;
import net.openhft.lang.io.ByteBufferBytes;
import net.openhft.lang.io.Bytes;
/**
 * 
 * Class with different fix Fields
 * 
 * @author Anshul Shelley
 *
 */
public class Field implements FixFieldInterface
{
    //protected HugeArray<Value> value;
    protected CharSequence name;
    protected int number;
    protected CharSequence required;
    protected FixFieldTypeInterface type;
    protected int valueSize;
    protected Bytes fieldData = new ByteBufferBytes(ByteBuffer.allocate(1024).order(ByteOrder.nativeOrder()));
    private static final byte MULTI_VALUE_DELIM = 1;    
      
    public Field setValueSize(int valueSize){
    	this.valueSize = valueSize;
    	return this;
    }
    
    public Field(){}
    
    /*public HugeArray<Value> getValue() {
        if (value == null) {            
            value = HugeCollections.newArray(Value.class, valueSize);
        }
        return this.value;
    }*/
    
    /**
     * Returns corresponding fix field name as per FixConstants.fieldsName[@number] for this message
     * @return name
     */
    public CharSequence getName() {
        return name;
    }

    /**
     * Sets corresponding fix field name as per FixConstants.fieldsName[@number] for this message
     */
    public void setName(CharSequence value) {
        this.name = value;
    }
    
    /**
     * Returns corresponding fix field ID as per FixConstants.fieldsNumber[@fieldID--used by parser] for this message
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets corresponding fix field ID as per FixConstants.fieldsNumber[@fieldID--used by parser]
     */
    public void setNumber(int value) {
        this.number = value;
    }

    public CharSequence getRequired() {
        return required;
    }

    public void setRequired(CharSequence value) {
        this.required = value;
    }

    public FixFieldTypeInterface getType() {
        return type;
    }

    public void setType(FixFieldTypeInterface value) {
        this.type = value;
    }

	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
	    //out.writeObject(value);
	    out.writeUTF((String)name);
	    out.writeInt(number);
	    out.writeUTF((String)required);
	    out.writeObject(type);
	    out.writeInt(valueSize);
	    
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//value = (HugeArray<Value>) in.readObject();
		name = in.readUTF();
	    number = in.readInt();
	    required = in.readUTF();
	    type = (FixFieldTypeInterface) in.readObject();
	    valueSize = in.readInt();
	}

	public Bytes getFieldData() {
		return fieldData;
	}
	
	public void setFieldData(Bytes bytes){
		this.fieldData = bytes;		
	}

	public static byte getMultiValueDelim() {
		return MULTI_VALUE_DELIM;
	}

	@Override
	public void reset() {
		
		this.fieldData = fieldData.zeroOut();		
		this.name = null;
		this.number = -1;
		this.required = null;
		this.type = null;
		this.valueSize = -1;
	}
	
	public void printValues(){
		System.out.println("name "+name);
	}

	public int getFieldDataPostion() {		
		return (int)fieldData.position();
	}
		
}
