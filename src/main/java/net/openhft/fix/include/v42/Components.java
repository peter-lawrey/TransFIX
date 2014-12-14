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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import net.openhft.lang.collection.HugeArray;

/*
 * This class is a placeholder for future implementation of FIX Components
 * */
public class Components implements FixMessageType
{

	@Override
	public <T> T getField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T setFieldSize(int fieldSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T setValueSize(int valueSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T setMessagesSize(int messageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T setGroupSize(int groupSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
