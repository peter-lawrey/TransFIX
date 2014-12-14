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
 * Represents a FIX protocol 4.2 field. A field's value is represented by ByteBufferBytes with a default size of 1MB. 
 *  
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
    protected ByteBufferBytes fieldData = new ByteBufferBytes(ByteBuffer.allocate(1024).order(ByteOrder.nativeOrder()));
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

    /* (non-Javadoc)
     * @see net.openhft.fix.include.v42.FixFieldInterface#getRequired()
     */
    public CharSequence getRequired() {
        return required;
    }

    /* (non-Javadoc)
     * @see net.openhft.fix.include.v42.FixFieldInterface#setRequired(java.lang.CharSequence)
     */
    public void setRequired(CharSequence value) {
        this.required = value;
    }

    /* (non-Javadoc)
     * @see net.openhft.fix.include.v42.FixFieldInterface#getType()
     */
    public FixFieldTypeInterface getType() {
        return type;
    }

    /* (non-Javadoc)
     * @see net.openhft.fix.include.v42.FixFieldInterface#setType(net.openhft.fix.model.FixFieldTypeInterface)
     */
    public void setType(FixFieldTypeInterface value) {
        this.type = value;
    }

	/**
	 * Used for serialization
	 * @param out
	 * @throws IOException
	 */
	void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
	    //out.writeObject(value);
	    out.writeUTF((String)name);
	    out.writeInt(number);
	    out.writeUTF((String)required);
	    out.writeObject(type);
	    out.writeInt(valueSize);
	    
	}

	/**
	 * Read serialzed bytes for this Field
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//value = (HugeArray<Value>) in.readObject();
		name = in.readUTF();
	    number = in.readInt();
	    required = in.readUTF();
	    type = (FixFieldTypeInterface) in.readObject();
	    valueSize = in.readInt();
	}

	/* (non-Javadoc)
	 * @see net.openhft.fix.include.v42.FixFieldInterface#getFieldData()
	 */
	public ByteBufferBytes getFieldData() {
		return fieldData;
	}
	
	/**
	 * Update ByteBufferBytes field data object
	 * @param bytes
	 */
	public void setFieldData(byte[] bytes){
		this.fieldData.write(bytes);
	}

	public void setFieldData(ByteBufferBytes bytes){
		this.fieldData.write(bytes);
	}
	
	/**
	 * If this field is repeated inside the FIX tag, use this delimiter for parsing. This is not related to Multi-leg data
	 * @return
	 */
	public static byte getMultiValueDelim() {
		return MULTI_VALUE_DELIM;
	}

	/* (non-Javadoc)
	 * @see net.openhft.fix.include.v42.FixFieldInterface#reset()
	 */
	@Override
	public void reset() {
		
		this.fieldData = (ByteBufferBytes) fieldData.zeroOut();		
		this.name = null;
		this.number = -1;
		this.required = null;
		this.type = null;
		this.valueSize = -1;
	}

	/**
	 * Position of this field's ByteBufferBytes
	 * @return
	 */
	public int getFieldDataPostion() {		
		return (int)fieldData.position();
	}
		
}
