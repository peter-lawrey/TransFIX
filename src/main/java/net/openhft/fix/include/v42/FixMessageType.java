package net.openhft.fix.include.v42;

import java.io.Externalizable;

/**
 * A FIX message should adhere to this type
 */
public interface FixMessageType extends Externalizable {

    /**
     * @return
     */
    <T> T getField();

    /**
     * @param fieldSize
     * @return
     */
    <T> T setFieldSize(int fieldSize);

    /**
     * @param valueSize
     * @return
     */
    <T> T setValueSize(int valueSize);

    /**
     * @return
     */
    <T> T getMessage();

    /**
     * @param messageSize
     * @return
     */
    <T> T setMessagesSize(int messageSize);

    /**
     * @param groupSize
     * @return
     */
    <T> T setGroupSize(int groupSize);


}
