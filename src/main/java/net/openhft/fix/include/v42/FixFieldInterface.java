/*
 * Copyright 2013 peter.lawrey Lawrey
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

import net.openhft.fix.model.FixFieldTypeInterface;
import net.openhft.lang.io.ByteBufferBytes;

/**
 * Fix field definition. This interface defines the required methods for implementation of
 * FIX protocol <Field>
 */
public interface FixFieldInterface {

    /**
     * @return- Returns name for this FIX field
     */
    CharSequence getName();

    /**
     * Mandatory or default name as per FIX protocol 4.2 definition
     *
     * @param name- Sets value for this FIX field
     */
    void setName(CharSequence name);

    /**
     * @return- Integer value for this field's index
     */
    int getNumber();

    /**
     * @param value- allows externally to reset field's index value. Default value usage as per
     * FIX protocol definition is recommended.
     */
    void setNumber(int value);

    /**
     * @return -Required value of this field
     */
    CharSequence getRequired();

    /**
     * @param value- allows to externally update this field's mandated value. Default value usage as
     * per FIX protocol definition is recommended.
     */
    void setRequired(CharSequence value);

    /**
     * @return- primitive JAVA type equivalent for this field.
     */
    FixFieldTypeInterface getType();

    /**
     * @param value- allows to externally update this field's mandated type. Default value usage as
     * per FIX protocol definition is recommended.
     */
    void setType(FixFieldTypeInterface value);

    /**
     * @return- Returns reference to FIX field's data. The default size of ByteBufferBytes used
     * is 1MB. Reverse look-up (just like a ByteBuffer read) is required
     */
    ByteBufferBytes getFieldData();

    /**
     * Resets ByteBufferBytes for this FIX field.
     */
    void reset();
}
