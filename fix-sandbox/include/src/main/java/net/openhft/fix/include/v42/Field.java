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

import net.openhft.fix.model.FixField;
import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Field 
{
    protected HugeArray<Value> value;
    protected String name;
    protected int number;
    protected String required;
    protected FixField type;
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

    public void setNumber(int value) {
        this.number = value;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String value) {
        this.required = value;
    }

    public FixField getType() {
        return type;
    }

    public void setType(FixField value) {
        this.type = value;
    }

}
