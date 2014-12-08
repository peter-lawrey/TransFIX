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

package net.openhft.fix.include.util;
import java.io.Externalizable;
import net.openhft.fix.include.v42.FixMessage;

/**
 * Interface for defining a FixMessage Object Factory. An implementation of this interface is required for 
 * intializing FixMessagePool.
 *
 * @param <FixMessage>
 */
@SuppressWarnings("hiding")
public interface FixPoolFactory <FixMessage> extends Externalizable{	
		/**
		 * This method creates a new instance of FixMessage object to be used by the FixMessagePool. 
		 * A FixMessage created from this method should have all its fields initialized for pre-allocation
		 * of memory required to be re-used for each one of its fields. Each Field object of this FixMessage object
		 * uses ByteBufferBytes for re-use purpose.
		 * 
		 * @param useDefault -Choose to use defaults inside FixConfig
		 * @return -a newly created FixMessage Object
		 */
		public FixMessage create(boolean useDefault);	
}
