package net.openhft.fix.include.v42;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Header 
{
    protected HugeArray<Field> field;
    protected int fieldCount;
     
    public HugeArray<Field> getField() {
        if (field == null) {
        	field = HugeCollections.newArray(Field.class, fieldCount);        	
        }
        return this.field;
    }
    
    public Header setFieldCount(int fieldCount){
    	this.fieldCount = fieldCount;
    	return this;
    }
}
