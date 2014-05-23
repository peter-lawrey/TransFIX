package net.openhft.fix.include.v42;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Message 
{
    protected HugeArray<Field> field;
    protected HugeArray<Group> group;
    protected String msgcat;
    protected String msgtype;
    protected String name;
    protected int fieldSize;
    protected int groupSize;

    public Message setFieldSize(int fieldSize){this.fieldSize=fieldSize;return this;}
    public Message setGroupSize(int groupSize){this.groupSize=groupSize;return this;}
    
    public HugeArray <Field> getField() {
        if (field == null) {
            field = HugeCollections.newArray(Field.class, fieldSize);
        }
        return this.field;
    }
    
    public HugeArray <Group> getGroup() {
        if (group == null) {
        	group = HugeCollections.newArray(Group.class, groupSize);
        }
        return this.group;
    }

    public String getMsgcat() {
        return msgcat;
    }

    public void setMsgcat(String value) {
        this.msgcat = value;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String value) {
        this.msgtype = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

}
