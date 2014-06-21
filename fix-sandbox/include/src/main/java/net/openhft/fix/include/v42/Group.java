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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Group implements Externalizable
{

    protected HugeArray<Field> field;
    protected HugeArray<Group> group;
    protected String name;
    protected String required;
	protected int fieldSize;
	protected int groupSize;

    public Group setFieldSize(int fieldSize){this.fieldSize = fieldSize;return this;}
    public Group setGroupSize(int groupSize){this.groupSize = groupSize;return this;}
    
    public HugeArray<Field> getField() {
        if (field == null) {
        	field = HugeCollections.newArray(Field.class, fieldSize);
        }
        return this.field;
    }
    
    public HugeArray<Group> getGroup() {
        if (group == null) {
        	group = HugeCollections.newArray(Group.class, groupSize);
        }
        return this.group;
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
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(field);
		out.writeObject(group);
		out.writeUTF(name);
		out.writeUTF(required);
		out.writeInt(fieldSize);
		out.writeInt(groupSize);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		field= (HugeArray<Field>) in.readObject();
		group= (HugeArray<Group>) in.readObject();
		name= in.readUTF();
		required= in.readUTF();
		fieldSize= in.readInt();
		groupSize= in.readInt();
		
		
	}

}
