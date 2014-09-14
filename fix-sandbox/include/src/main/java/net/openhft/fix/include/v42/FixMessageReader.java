package net.openhft.fix.include.v42;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import net.openhft.fix.compiler.FieldLookup;
import net.openhft.fix.include.util.FixConstants;
import net.openhft.lang.io.ByteBufferBytes;
import net.openhft.lang.io.Bytes;
import net.openhft.lang.io.DirectStore;
import net.openhft.lang.io.NativeBytes;
import net.openhft.lang.io.StopCharTesters;
import net.openhft.fix.model.FixField;
/**
 * 
 * This class is used for parsing a FIX 4.2 message. It follows a standard Fix4.2 protocol Field-specification that adheres to FixCommunity.org  definition.
 * 
 * @author Anshul Shelley
 *
 */
public class FixMessageReader {
	
	@SuppressWarnings("unused")
	private CharSequence fixMsgChars;
	
	@SuppressWarnings("unused")
	private Bytes fixMsgBytes;
	private final int FIELD_SIZE=FixConstants.fieldsNumber.length;
	private Field field[] = new Field[FIELD_SIZE];
	
	public Field[] getField() {
		return field;
	}

	private StringBuilder tempStringValue= new StringBuilder();
	private final byte FIELD_TERMINATOR = 1;
	private byte VERSION_CHECKED=0;
	private Field fieldFill = new Field();
	
	//private HugeArray <FixFieldInterface> fixField = HugeCollections.newArray(FixFieldInterface.class, FIELD_SIZE);
	
	/**
	 * Accepts a new Fix Message ByteBufferBytes. This method is a precursor to parseFixMsgBytes()
	 * @param fixMsgBufBytes
	 */
	public void setFixBytes(ByteBufferBytes fixMsgBufBytes){
		if (this.fixMsgBytes != null){this.fixMsgBytes.clear();}
		this.fixMsgBytes = fixMsgBufBytes;
		fieldFill.reset();
		Arrays.fill(field, 0,field.length,fieldFill);
	}
	/**
	 * A CharSequence of raw fix message is converted to NativeBytes. This method is a precursor to parseFixMsgBytes()
	 * @param fixMsgChars
	 */
	public void setFixBytes(CharSequence fixMsgChars)
	{
		this.fixMsgChars = fixMsgChars;
		NativeBytes nativeBytes = new DirectStore(fixMsgChars.length()).bytes();
		nativeBytes.write(((String) fixMsgChars).replace('|', '\u0001').getBytes());
		if (fixMsgBytes != null){this.fixMsgBytes.clear();}
		fixMsgBytes = nativeBytes.flip();
		fieldFill.reset();
		Arrays.fill(field, 0,field.length, fieldFill);
	}
	
	/**
	 * Directly uses NativeBytes for parsing. This method is a precursor to parseFixMsgBytes(). Performance of NativeBytes is better during higher load situations
	 * @param fixNativeBytes
	 */
	public void setFixBytes(NativeBytes fixNativeBytes)
	{
		if (fixMsgBytes != null){this.fixMsgBytes.clear();}
		fixMsgBytes = fixNativeBytes.flip();
		fieldFill.reset();
		Arrays.fill(field, 0,field.length, fieldFill);
	}

	/**
	 * Only support FIX 4.2 version. Parses fixMessage and return an array of Field objects. Precursor function to setFixBytes() else throws Exception
	 * A Field array index is defined by FixConstants.fieldsNumber
	 * 
	 * As an example 
	 * Field fixField = Field[8];
	 * System.out.println("Fix Field Name:"+fixField.getName());
	 * Prints BeginString;
	 * 
	 * @return Field []
	 * @throws Exception
	 */
	public Field[] parseFixMsgBytes() throws Exception{
		
		if (fixMsgBytes == null ){throw new Exception("Bytes is null or not preceded by setFixBytes()");}
		
		long limit = fixMsgBytes.limit(), limit2 = limit;
        while (limit2 > fixMsgBytes.position() && fixMsgBytes.readByte(limit2 - 1) != FIELD_TERMINATOR)
            limit2--;
        fixMsgBytes.limit(limit2);
        boolean tmpSelf = fixMsgBytes.selfTerminating();
        try {
        	fixMsgBytes.selfTerminating(true);
            while (fixMsgBytes.remaining() > 0) {
                int fieldNum = (int)fixMsgBytes.parseLong();
                long pos = fixMsgBytes.position();

                searchForTheEndOfField(fixMsgBytes);

                long end = fixMsgBytes.position() - 1;
                fixMsgBytes.limit(end);
                fixMsgBytes.position(pos);
                updateFixMessageFields(fieldNum, fixMsgBytes);

                fixMsgBytes.limit(limit);
                fixMsgBytes.position(end + 1);                
            }
            fixMsgBytes.limit(limit);
            fixMsgBytes.position(limit2);
        } finally {
        	fixMsgBytes.selfTerminating(tmpSelf);
        }
        return field;
	}
	/**
	 * 
	 * @param bytes
	 */
	private void searchForTheEndOfField(Bytes bytes) {
        while (bytes.readByte() != FIELD_TERMINATOR) ;
    }
	
	/**
	 * 
	 * @param fieldID
	 * @param fieldValue
	 * @throws Exception
	 */
	private void updateFixMessageFields(int fieldID, Bytes fieldValue) throws Exception{
		
		if (field[fieldID] ==  null){System.out.println("NULL...."+fieldID);System.exit(0);}
		field[fieldID].setName(FixConstants.fieldsName[fieldID]);
		field[fieldID].setNumber(fieldID);
		FixField ff = FieldLookup.fieldFor(FixConstants.fieldsTypeOrdering[fieldID+1]);
		
		if (ff.isChar())
		{
			field[fieldID].setType(FixField.Boolean);
			if (field[fieldID].getFieldData().position() !=0 ){
				field[fieldID].getFieldData().writeByte(Field.getMultiValueDelim());//adding delim for multi values
			}
			field[fieldID].getFieldData().writeChar(fieldValue.readChar());
		}
		else if (ff.isDouble())
		{
			field[fieldID].setType(FixField.Double);
			if (field[fieldID].getFieldData().position() !=0 ){
				field[fieldID].getFieldData().writeByte(Field.getMultiValueDelim());
			}
			field[fieldID].getFieldData().writeDouble(fieldValue.parseDouble());
		}
		else if (ff.isInt())
		{
			field[fieldID].setType(FixField.Int);
			if (field[fieldID].getFieldData().position() !=0 ){
				field[fieldID].getFieldData().writeByte(Field.getMultiValueDelim());
			}
			field[fieldID].getFieldData().writeInt24((int)fieldValue.parseLong());
		}
		else if (ff.isLong())
		{
			field[fieldID].setType(FixField.Length);
			if (field[fieldID].getFieldData().position() !=0 ){
				field[fieldID].getFieldData().writeByte(Field.getMultiValueDelim());
			}
			field[fieldID].getFieldData().writeLong(fieldValue.parseLong());
			
		}else if (ff.isString())
		{					
			field[fieldID].setType(FixField.String);
			if (field[fieldID].getFieldData().position() !=0 ){
				field[fieldID].getFieldData().writeByte(Field.getMultiValueDelim());
			}
			tempStringValue.setLength(0);
			fieldValue.parseUTF(tempStringValue, StopCharTesters.ALL);
			if (VERSION_CHECKED ==0 && fieldID==8)
			{
				if (tempStringValue.toString().equalsIgnoreCase("FIX.4.2")){
					VERSION_CHECKED=0;					
				}
				else {					
					throw new Exception("Only FIX.4.2 supported");
				}
			}
			field[fieldID].getFieldData().write(tempStringValue.toString().getBytes());//???to not use toString()			
		}
		//field[fieldID].printValues();
	}
	
	public static void main (String [] args) throws Exception
	{
		String sampleFixMessage = "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=FIA.MI|54=2|27=250000|44=7900.000000|25=H|10=231|";
		FixMessageReader fmr = new FixMessageReader();		
		/*try {
			fmr.parseFixMsgBytes();
			System.out.println("Parsing done...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		NativeBytes nativeBytes = new DirectStore(sampleFixMessage.length()).bytes();
		nativeBytes.write(sampleFixMessage.replace('|', '\u0001').getBytes());
		//ByteBufferBytes byteBufBytesNative = (ByteBufferBytes)((Bytes)nativeBytes.flip());
		//fmr.setFixBytes((ByteBufferBytes)(Bytes)nativeBytes.flip());
		byte [] msgBytes = sampleFixMessage.replace('|', '\u0001').getBytes();
		ByteBufferBytes byteBufBytes = new ByteBufferBytes(ByteBuffer.allocate(msgBytes.length).order(ByteOrder.nativeOrder()));
		byteBufBytes.write(msgBytes);
		
		int counter= 0;
        int runs = 300000;
        long start = System.nanoTime();
        for (int i = 0; i < runs; i++) { 
        	fmr.setFixBytes(byteBufBytes);
            fmr.parseFixMsgBytes();
            counter++;
        }
        long time = System.nanoTime() - start;
        System.out.printf("Average parse time was %.2f us, fields per message %.2f%n", time / runs / 1e3, (double) counter / runs);
		
	}
}
