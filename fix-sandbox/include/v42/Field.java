package net.openhft.fix.include.v42;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Field 
{
    protected HugeArray<Value> value;
    protected String name;
    protected double number;
    protected String required;
    protected String type;
    protected int valueSize;
      
    public Field setValueSize(int valueSize){
    	this.valueSize = valueSize;
    	return this;
    }
    
    public HugeArray<Value> getValue() {
        if (value == null) {            
            value = HugeCollections.newArray(Value.class, valueSize);
        }
        return this.value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double value) {
        this.number = value;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String value) {
        this.required = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

}
