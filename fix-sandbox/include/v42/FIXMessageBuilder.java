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

import net.openhft.fix.include.util.FixConfig;
import net.openhft.lang.model.DataValueGenerator;


public class FIXMessageBuilder implements Cloneable 
{	
	private Header header;
	private Messages messages;
	private Trailer trailer;
	private Components comp;
	private Fields fields;
	private final DataValueGenerator dvg = new DataValueGenerator();
	private FixConfig fixConfig;
    
	public FIXMessageBuilder clone() {
        try {
            return (FIXMessageBuilder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
	
	public FixMessage createFixMessage(boolean useDefault){
		if (useDefault){
			fixConfig = new FixConfig().SERVER_DEFAULT_4_2.clone()
					.createServerFixHeader()
					.createServerFixMessages()
					.createServerFixComponents()
					.createServerFixTrailer()
					.createServerFixFields();
			
			this.header=fixConfig.getHeader();
			this.messages=fixConfig.getMessages();
			this.trailer=fixConfig.getTrailer();
			this.comp=fixConfig.getComponents();
			this.fields=fixConfig.getFields();
		}
		return new FixMessage(this);
	}
	
   
    public FIXMessageBuilder createHeader(int fieldCount) {
    	 this.header = dvg.nativeInstance(Header.class);
     this.header.setFieldCount(fieldCount).getField();
        
        return this;
    }

    public FIXMessageBuilder createMessages(int messagesSize, int fieldSize, int groupSize) {
        this.messages = dvg.nativeInstance(Messages.class);
        this.messages.setMessagesSize(messagesSize).setFieldSize(fieldSize).setGroupSize(groupSize).getMessage();
        return this;
    }	
    
    public FIXMessageBuilder createTrailer(int fieldSize) {
        this.trailer = dvg.nativeInstance(Trailer.class);
        this.trailer.getField();//not setting the size since default are only 3 fields.
        return this;
	}
    
    public FIXMessageBuilder createComponents() {
        return this;
    }

    public FIXMessageBuilder createFields(int fieldSize, int valueSize) {        
        this.fields = dvg.nativeInstance(Fields.class);
        this.fields.setFieldSize(fieldSize).setValueSize(valueSize);
        return this;
    }
    
    public Header getHeader() {
		return header;
	}

	public Messages getMessages() {
		return messages;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public Components getComp() {
		return comp;
	}

	public Fields getFields() {
		return fields;
	}
	
	
	public static void main (String ...args){
		
		FixMessage fm = new FIXMessageBuilder().createFixMessage(true);
	}

}
