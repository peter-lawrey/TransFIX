package net.openhft.fix.include.v42;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;


public class Trailer 
{
    protected HugeArray<Field> field;
    protected int fieldSize=3;

    public Trailer (int fieldSize){
    	this.fieldSize=fieldSize;    	
    }
    
    public HugeArray <Field> getField() {
        if (field == null) {
            field = HugeCollections.newArray(Field.class, fieldSize);
        }
        return this.field;
    }
}
