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

package net.openhft.fix.include.util;

import java.nio.ByteBuffer;

import net.openhft.compiler.CachedCompiler;
import net.openhft.fix.compiler.FieldLookup;
import net.openhft.fix.include.v42.Field;
import net.openhft.fix.include.v42.Fields;
import net.openhft.fix.include.v42.FixMessageType;
import net.openhft.lang.io.ByteBufferBytes;
import net.openhft.lang.io.Bytes;
import net.openhft.lang.model.Byteable;
import net.openhft.lang.model.DataValueGenerator;

/**
 * Default configuration class for a Fix 4.2 message. Uses static methods to initialize FIX 4.2
 * header and fields. The main purpose of this class is to initialize standard fix 4.2 fields
 * to allocate memory before usage to avoid dynamic allocation of ByteBufferBytes during runtime
 * for FIX fields data.
 */
public class FixConfig implements Cloneable {

    private int fixVersionMajor;
    private int fixVersionMinor;
    private int fixVersionServicePack;

    private int currentFixVersion;
    private int fix4_2_0_mask 				= 0x01A4;
    private Fields fields;
    private Field[] fieldArr;

    public FixConfig clone() {
        try {
            return (FixConfig) super.clone();
        } catch ( CloneNotSupportedException e ) {
            throw new AssertionError( e );
        }
    }

    public static FixConfig SERVER_DEFAULT_4_2 = new FixConfig()
            .setFixVersionMajor( 4 )
            .setFixVersionMinor( 2 )
            .setFixVersionServicePack( 0 )
            .createServerFixFields( );

    /**
     * Static factory method to initializes FIX message header to default Fix 4.2 version
     *
     * @return -FixConfig
     */
    public FixConfig createServerFixHeader() {
        if ( ( currentFixVersion & fix4_2_0_mask ) != 0 ) {
            try {
                load42DefaultHeader();
            } catch ( Exception e ) {
                throw new AssertionError( e );
            }
        }
        return this;
    }

    @SuppressWarnings("deprecation")
	private void load42DefaultHeader() throws Exception {
        String actual = new DataValueGenerator().generateNativeObject( FixMessageType.class );
        CachedCompiler cc = new CachedCompiler( null, null );
        Class<?> aClass = cc.loadFromJava( FixMessageType.class.getName() + "$$Native", actual );

        FixMessageType fmt = ( FixMessageType ) aClass.asSubclass( FixMessageType.class ).newInstance();
        Bytes bytes = new ByteBufferBytes( ByteBuffer.allocate( 1024 * 1024 ));
        ( (Byteable) fmt).bytes( bytes, 0L );

    }

    @SuppressWarnings("unused")
    private FixConfig createServerFixComponents() {
        return this;
    }

    /**
     * Static factory method to initializes all the standard FIX <Field> of FIX 4.2 version
     *
     * @return -FixConfig
     */
    public FixConfig createServerFixFields() {
        if ((currentFixVersion & fix4_2_0_mask) != 0) {
            load42DefaultFields();
        }
        return this;
    }


    /**
     * Returns an array of all the initialized Fix Fields
     *
     * @return -Field[]
     */
    public Field[] getFieldArr() {
        return fieldArr;
    }

    private void load42DefaultFields() {
        fieldArr = new Field[ FixConstants.fieldsNumber.length ];
        for (short i = 0; i < FixConstants.fieldsNumber.length; i++) {
            fieldArr[i] = new Field();
            fieldArr[i].setNumber(FixConstants.fieldsNumber[i]);
            fieldArr[i].setName(FixConstants.fieldsName[i]);
            fieldArr[i].setType(FieldLookup.fieldFor(FixConstants.fieldsTypeOrdering[i]));
        }
    }

    /**
     * Returns Fix version 'Major'; like 3 or 4 or 5
     *
     * @return - fixVersionMajor
     */
    public int getFixVersionMajor() {
        return fixVersionMajor;
    }


    /**
     * Static factory method to set FIX protocol version-major
     *
     * @param fixVersionMajor - major version intvalue
     * @return - FixConfig
     */
    public FixConfig setFixVersionMajor(int fixVersionMajor) {
        this.fixVersionMajor = fixVersionMajor;
        return this;
    }

    /**
     * Returns minor value of the FIX protocol version like 2 or 4 for Fix 4.2 or 4.4
     *
     * @return -fixVersionMinor
     */
    public int getFixVersionMinor() {
        return fixVersionMinor;
    }

    /**
     * Static factory method to set FIX protocol version-minor
     *
     * @param fixVersionMinor-minor version intvalue
     * @return -FixConfig
     */
    public FixConfig setFixVersionMinor(int fixVersionMinor) {
        this.fixVersionMinor = fixVersionMinor;
        return this;
    }

    /**
     * Returns Fix Protocol service Pack value for this FIX object
     *
     * @return -fixVersionServicePack
     */
    public int getFixVersionServicePack() {
        return fixVersionServicePack;
    }

    /**
     * Static factory method to set Fix Protocol service Pack value
     * @param fixVersionServicePack - fix servicepack
     * @return -FixConfig
     */    
    public FixConfig setFixVersionServicePack(int fixVersionServicePack) {
        this.fixVersionServicePack = fixVersionServicePack;
        setCurrentFixVersion();
        return this;
    }

    private void setCurrentFixVersion() {
        currentFixVersion = fixVersionMajor | fixVersionMinor | fixVersionServicePack;
    }

    /**
     * Returns Fields object for this FIX message
     *
     * @return -fields
     */
    public Fields getFields() {
        return fields;
    }
}
