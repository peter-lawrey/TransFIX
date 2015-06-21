package net.openhft.fix.model;

/**
 * Interface to identify the corresponding FIX field type to java primitive data types
 */
public interface FixFieldTypeInterface {

    /**
     * @return - true if field is an Integer
     */
    boolean isInt();

    /**
     * @return- true if field is a Long
     */
    boolean isLong();

    /**
     * @return- true if field is a Float
     */
    boolean isFloat();

    /**
     * @return- true if field is a double
     */
    boolean isDouble();

    /**
     * @return- true if field is a Character
     */
    boolean isChar();

    /**
     * @return- true if field is a String
     */
    boolean isString();

    /**
     * @return- true if field is a boolean
     */
    boolean isBoolean();

    /**
     * @return - true if field is a Pattern
     */
    boolean isPattern();
}
