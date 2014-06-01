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
