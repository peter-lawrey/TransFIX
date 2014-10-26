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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import net.openhft.fix.include.util.FixConstants;
import net.openhft.lang.io.ByteBufferBytes;

public class FixMessage implements FixMessageInterface 
{
	protected int major=4;
    protected int minor=2;
    protected int servicepack=0;
    protected CharSequence type="FIX";
    private StringBuilder fixMsgOutput;
    private char delim = '|';
    private char equalsChar = '=';
    private Field[] field;
    
    public FixMessage(FIXMessageBuilder fixMsgBuilder) {
    	
	}
    
    public Field[] getField(){
    	return field;
    }
    
    public FixMessage(Field[] field){
    	this.field = field;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getServicepack() {
        return servicepack;
    }

    public CharSequence getType() {
        return type;
    }
    
    public Field getField(int fieldLocation){
    	//System.out.println(fieldLocation +"====="+FixConstants.fieldsNumber[FixConstants.fieldsNumber.length-1]);
    	if (fieldLocation < FixConstants.fieldsNumber[FixConstants.fieldsNumber.length-1]){
    		return this.field[fieldLocation-1];
    	}
    	
    	return null;
    }
    
    public String getFixString(){//depends on your business logic
    	//8|9|35|34
    	int msgSize = 0;
    	this.fixMsgOutput = new StringBuilder();
    	this.fixMsgOutput.setLength(0);
    	this.fixMsgOutput.append(FixConstants.fieldsNumber[7]);
    	this.fixMsgOutput.append(this.equalsChar);
    	this.fixMsgOutput.append(this.type);    	
    	this.fixMsgOutput.append(this.major);
    	this.fixMsgOutput.append(this.minor);
    	this.fixMsgOutput.append(this.servicepack);
    	this.fixMsgOutput.append(this.delim);
    	if (field != null)
    	{
	    	for (int i=0;i<field.length;i++)
	    	{
	    		if (field[i].getFieldDataPostion() > 0 && i != 8)
	    		{
	    			System.out.println(field[i].getFieldDataPostion());
		    		this.fixMsgOutput.append(field[i].getNumber());	    				
		    		this.fixMsgOutput.append(this.equalsChar);		    		
		    		this.fixMsgOutput.append(field[i].getFieldData().readUTF());
		    		this.fixMsgOutput.append(this.delim);
		    		msgSize += field[i].getFieldDataPostion();
	    		}
	    	}
	    	this.fixMsgOutput.append(field[8].getNumber());	    				
    		this.fixMsgOutput.append(this.equalsChar);		    		
    		this.fixMsgOutput.append(msgSize);
    		this.fixMsgOutput.append(this.delim);   	
    	}
       	return this.fixMsgOutput.toString();
    }

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(major);
		out.writeInt(minor);
		out.writeInt(servicepack);
		out.writeUTF((String)type);
		out.writeObject(this.fixMsgOutput);
		out.writeChar(delim);
	}
	
	public int getCheckSum(){
		int checksum=0;
		if (field != null){
			for (int i=0; i<field.length;i++){
				checksum += field[i].getFieldData().position();
			}
		}
		return checksum % 256;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException 
	{
		major= in.readInt();
		minor= in.readInt();
		servicepack= in.readInt();
		type = in.readUTF();
		this.fixMsgOutput= (StringBuilder) in.readObject();
		delim= in.readChar();
		
	}
	
	//checks if the session information of these tags is valid 8|9|34|35|49|56|10 
	public int isValid(){
		int validTagArray [] = {8,9,34,35,49,56,10};		
		for (int i=0; i<validTagArray.length;i++){
			if (field[validTagArray[i]-1].getFieldData().position() < 1 ){
				return 0;				
			}		
		}
		
		return 1;
	}
	
	
	/**
	 * Resets Field[] data
	 */
	public void reset(){		
		if (field != null){
			for (int i=0; i<field.length;i++){
				field[i].reset();
			}
		}		
	}

}
