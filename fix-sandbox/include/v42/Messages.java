package net.openhft.fix.include.v42;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Messages 
{
    protected HugeArray<Message> messageArr;
    protected int messagesSize;   
    protected int fieldSize;
    protected int groupSize;
    
    
    public Messages setMessagesSize(int messagesSize){
    	this.messagesSize=messagesSize;
    	return this;
    }
    
    public Messages setFieldSize(int fieldSize){this.fieldSize=fieldSize;return this;}
    public Messages setGroupSize(int groupSize){this.groupSize=groupSize;return this;}
        
    public HugeArray<Message> getMessage() {
        if (messageArr == null) {
            messageArr = HugeCollections.newArray(Message.class, messagesSize);
            for (int i=0;i<messagesSize;i++)
            {
            	Message message = messageArr.get(i);
            	message.setFieldSize(fieldSize);
            	message.setGroupSize(groupSize);
            }
        }
        return this.messageArr;
    }

}
