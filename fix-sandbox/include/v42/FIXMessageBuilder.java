package net.openhft.fix.include.v42;

import net.openhft.lang.model.DataValueGenerator;

public class FIXMessageBuilder implements Cloneable 
{	
	private Header header;
	private Messages messages;
	private Trailer trailer;
	private Components comp;
	private Fields fields;
	private final DataValueGenerator dvg = new DataValueGenerator();
    
	public FIXMessageBuilder clone() {
        try {
            return (FIXMessageBuilder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
	
	public FixMessage createFixMessage(){		
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

}
