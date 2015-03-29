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

import net.openhft.fix.include.util.FixConfig;
import net.openhft.fix.include.util.FixMessagePool;
import net.openhft.lang.model.DataValueGenerator;

/**
 * This class is used for creating off-heap message objects for the corresponding FIX protocol
 * object
 */
public class FIXMessageBuilder implements Cloneable {
    private Header header;
    private Messages messages;
    private Trailer trailer;
    private Components comp;
    private Fields fields;
    private final DataValueGenerator dvg = new DataValueGenerator();
    private FixConfig fixConfig;
    private int poolSize = 10;

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public FIXMessageBuilder clone() {
        try {
            return (FIXMessageBuilder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Initializes and returns a Fix Object pool Object.
     *
     * @param useDefault
     * @param poolSize
     * @return
     */
    public FixMessagePool initFixMessagePool(boolean useDefault, int poolSize) {
        this.poolSize = poolSize;
        /*if (useDefault){
			fixConfig = new FixConfig().SERVER_DEFAULT_4_2.clone()
					//.createServerFixHeader()
					//.createServerFixMessages()
					//.createServerFixComponents()
					//.createServerFixTrailer()
					.createServerFixFields();
			
			//this.header=fixConfig.getHeader();
			//this.messages=fixConfig.getMessages();
			//this.trailer=fixConfig.getTrailer();
			//this.comp=fixConfig.getComponents();
			this.fields=fixConfig.getFields();
		}
		return new FixMessagePool(this);
		*/
        if (useDefault) {
            return new FixMessagePool(null, poolSize, useDefault);
        }

        return null;
    }


    /**
     * Creates FIX protocol header
     *
     * @param fieldCount
     * @return
     */
    public FIXMessageBuilder createHeader(int fieldCount) {
        this.header = dvg.nativeInstance(Header.class);
        this.header.setFieldSize(fieldCount).getField();

        return this;
    }

    /**
     * Creates FIX protocol Messages
     *
     * @param messagesSize
     * @param fieldSize
     * @param groupSize
     * @return
     */
    public FIXMessageBuilder createMessages(int messagesSize, int fieldSize, int groupSize) {
        this.messages = dvg.nativeInstance(Messages.class);
        this.messages.setMessagesSize(messagesSize).setFieldSize(fieldSize).setGroupSize(groupSize)
                .getMessage();
        return this;
    }

    /**
     * Creates FIX Protocol Trailer
     *
     * @param fieldSize
     * @return
     */
    public FIXMessageBuilder createTrailer(int fieldSize) {
        this.trailer = dvg.nativeInstance(Trailer.class);
        this.trailer.getField();//not setting the size since default are only 3 fields.
        return this;
    }

    /**
     * Currently un-implemented since all the field headers are included inside the Field object
     *
     * @return-
     */
    public FIXMessageBuilder createComponents() {
        return this;
    }

    /**
     * @param fieldSize
     * @param valueSize
     * @return
     */
    public FIXMessageBuilder createFields(int fieldSize, int valueSize) {
        this.fields = dvg.nativeInstance(Fields.class);
        this.fields.setFieldSize(fieldSize).setValueSize(valueSize);
        return this;
    }

    /**
     * @return - Header object
     */
    public Header getHeader() {
        return header;
    }

    /**
     * @return - Message object
     */
    public Messages getMessages() {
        return messages;
    }

    /**
     * @return -trailer Object
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * @return -components object
     */
    public Components getComp() {
        return comp;
    }

    /**
     * @return - Reference to Fields object that contains individual Field object for this FIX object
     */
    public Fields getFields() {
        return fields;
    }

}
