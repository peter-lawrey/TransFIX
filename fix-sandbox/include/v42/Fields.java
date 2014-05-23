package net.openhft.fix.include.v42;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Fields 
{
   protected HugeArray<Field> fieldArr;
   protected int fieldSize=446;//fix42 has 446 fields
   protected int valueSize;
        
   public Fields setFieldSize(int fieldSize){this.fieldSize=fieldSize;return this;}
   public Fields setValueSize(int valueSize){this.valueSize=valueSize;return this;}
   
   public HugeArray<Field> getField() {
        if (fieldArr == null) {
            fieldArr = HugeCollections.newArray(Field.class, fieldSize);
            for (int i=0;i<fieldSize;i++)
            {
            	Field field = fieldArr.get(i);
            	field.setValueSize(valueSize);
            }
        }
        return this.fieldArr;
    }

}
