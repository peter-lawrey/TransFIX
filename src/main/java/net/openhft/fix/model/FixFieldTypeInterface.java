package net.openhft.fix.model;

/**
 * Interface to identify the corresponding FIX field type to java primitive data types
 *
 */
public interface FixFieldTypeInterface {

    /**
     * @return - true if field is an Integer
     */
    public boolean isInt() ;
    
    /**
     * @return- true if field is a Long
     */
    public boolean isLong();

    /**
     * @return- true if field is a Float
     */
    public boolean isFloat() ;
    
    /**
     * @return- true if field is a double
     */
    public boolean isDouble() ;

    /**
     * @return- true if field is a Character
     */
    public boolean isChar();
    
    /**
     * @return- true if field is a String
     */
    public boolean isString() ;
    
    /**
     * @return- true if field is a boolean
     */
    public boolean isBoolean() ;

    /**
     * @return - true if field is a Pattern
     */
    public boolean isPattern() ;
	
}
