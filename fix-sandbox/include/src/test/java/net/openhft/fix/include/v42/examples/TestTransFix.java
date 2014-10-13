package net.openhft.fix.include.v42.examples;

import net.openhft.fix.include.util.FixMessagePool;
import net.openhft.fix.include.util.FixMessagePool.FixMessageContainer;
import net.openhft.fix.include.v42.FIXMessageBuilder;
import net.openhft.fix.include.v42.Field;
import net.openhft.fix.include.v42.FixMessage;
import net.openhft.fix.include.v42.FixMessageReader;
import net.openhft.lang.io.ByteBufferBytes;
import net.openhft.lang.io.DirectStore;
import net.openhft.lang.io.NativeBytes;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TestTransFix {
	
	
	public static void main (String[] args) throws Exception{
		
		TestTransFix ttf = new TestTransFix();
		ttf.readEditModifyFixMsg();	
		
	}
	
	private void readEditModifyFixMsg() throws Exception {
		String sampleFixMessage = "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|10=231|";
		
		int fixMsgCount = Runtime.getRuntime().availableProcessors();
		
		//create fix message pool with default configuration for each FixMessage
		FixMessagePool fmp = new FIXMessageBuilder().initFixMessagePool(true, fixMsgCount);
		FixMessageContainer<FixMessage> fmc = fmp.getFixMessageContainer();
		
		//check out a FixMessage object
		FixMessage fm = fmc.getFixMessage();
		
		//create an instance of FixMessageReader instance for parsing
		FixMessageReader fmr = new FixMessageReader();
		
		//An instance of NativeBytes for converting a String to bytes data 
		NativeBytes nativeBytes = new DirectStore(sampleFixMessage.length()).bytes();
		nativeBytes.write(sampleFixMessage.replace('|', '\u0001').getBytes());
		byte [] msgBytes = sampleFixMessage.replace('|', '\u0001').getBytes();
		ByteBufferBytes byteBufBytes = new ByteBufferBytes(ByteBuffer.allocate(msgBytes.length).order(ByteOrder.nativeOrder()));
		byteBufBytes.write(msgBytes);
	   	
		//setting and parsing the fix message
		fmr.setFixBytes(byteBufBytes);		
        fmr.parseFixMsgBytes();
        
        //gets a Field array with parsed data
        Field[] field = fmr.getField();
        
        //Sets a checkedout FixMessage instance object with the FIX message information.
        for (int i=0;i<field.length;i++){
        	fm.getField(i).setFieldData(field[i].getFieldData());
        }   
         		
	}
	
	@Test
	public void updateFixFieldForEmptyMessage(){
		int fixMsgCount = Runtime.getRuntime().availableProcessors();
		FixMessagePool fmp = new FIXMessageBuilder().initFixMessagePool(true, fixMsgCount);
		FixMessageContainer<FixMessage> fmc = fmp.getFixMessageContainer();
		FixMessage fm =  fmc.getFixMessage();		
		ByteBufferBytes bbUser = new ByteBufferBytes(ByteBuffer.allocate(1024).order(ByteOrder.nativeOrder()));
		
		//assertEquals(0, fm.getField(8).getFieldData().position());
		
		byte[] fixHeader = "FIX.4.2".getBytes();
		fm.getField(8).setFieldData(fixHeader);//write into FixMessage field 8		
	//	assertEquals(fixHeader.length, fm.getField(8).getFieldData().position());
		
		//bbUser.write(fixVersion);//write into ByteBuffer		
		
		System.out.println(fixHeader.length+ "=?==" + fm.getField(8).getFieldData().position());
	}
	
	
	
}
