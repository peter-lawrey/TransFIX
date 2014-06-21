package net.openhft.fix.include.v42;

import net.openhft.fix.model.FixFieldTypeInterface;
import net.openhft.lang.io.Bytes;

/**
 * Fix field definition. 
 * 
 * @author Anshul Shelley
 *
 */

public interface FixFieldInterface {
	
	CharSequence getName();
	void setName(CharSequence name);
	int getNumber();
	void setNumber(int value);
	CharSequence getRequired();
	void setRequired(CharSequence value);
	FixFieldTypeInterface getType();
	void setType(FixFieldTypeInterface value);
	Bytes getFieldData();
	void reset();
}
