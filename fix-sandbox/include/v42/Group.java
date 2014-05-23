package net.openhft.fix.include.v42;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Group 
{

    protected HugeArray<Field> fieldOrGroup;
    protected String name;
    protected String required;
	protected int valueSize;

    public Group (int valueSize){this.valueSize = valueSize;}
    
    public HugeArray<Field> getFieldOrGroup() {
        if (fieldOrGroup == null) {
            fieldOrGroup = HugeCollections.newArray(Field.class, valueSize);
        }
        return this.fieldOrGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String value) {
        this.required = value;
    }

}
