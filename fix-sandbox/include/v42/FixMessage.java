package net.openhft.fix.include.v42;

public class FixMessage 
{
	protected Header header;
    protected Messages messages;
    protected Trailer trailer;
    protected Components components;
    protected Fields fields;
    
    protected int major=4;
    protected int minor=2;
    protected int servicepack=0;
    protected final String type="FIX";

    
    public FixMessage(FIXMessageBuilder fixMsgBuilder){
    	  this.header=fixMsgBuilder.getHeader();
    	  this.messages=fixMsgBuilder.getMessages();
    	  this.trailer=fixMsgBuilder.getTrailer();
    	  this.components=fixMsgBuilder.getComp();
    	  this.fields=fixMsgBuilder.getFields();
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
  
    public Components getComponents() {
        return components;
    }

    public Fields getFields() {
        return fields;
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

    public String getType() {
        return type;
    }

}
