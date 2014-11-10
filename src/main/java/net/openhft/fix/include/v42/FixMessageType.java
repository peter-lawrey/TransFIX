package net.openhft.fix.include.v42;

import java.io.Externalizable;
import net.openhft.lang.collection.HugeArray;

public interface FixMessageType extends Externalizable{
	
	<T> T getField();
	<T> T setFieldSize(int fieldSize);
	<T> T setValueSize(int valueSize);
	<T> T getMessage();
	<T> T setMessagesSize(int messageSize);
	<T> T setGroupSize(int groupSize);
	
	
}
