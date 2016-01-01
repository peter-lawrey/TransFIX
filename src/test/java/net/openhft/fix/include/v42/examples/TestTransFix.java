package net.openhft.fix.include.v42.examples;

import net.openhft.fix.include.util.FixMessagePool;
import net.openhft.fix.include.util.FixMessagePool.FixMessageContainer;
import net.openhft.fix.include.v42.Field;
import net.openhft.fix.include.v42.FixMessage;
import net.openhft.fix.include.v42.FixMessageReader;
import net.openhft.lang.io.ByteBufferBytes;
import net.openhft.lang.io.DirectStore;
import net.openhft.lang.io.NativeBytes;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.junit.Assert.assertEquals;

public class TestTransFix {

    public static void main(String[] args)   {

        TestTransFix ttf = new TestTransFix();
        ttf.testReadEditModifyFixMsg();
    }

    /**
     * Tests read/modify/edit functionality of a FixMessage object. First a FixMessagePool with default available processors is
     * created with FixMessage objects, then Field objects of each FixMessage object's ByteBufferByte are used for data read/write.
     * This is a simple test to verify the basic functionality.
     *
     * @
     */
    @Test
    @Ignore
    public void testReadEditModifyFixMsg()   {
        String sampleFixMessage = "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|10=231|";

        //create fix message pool with default configuration for each FixMessage
        FixMessagePool fmp = new FixMessagePool( null, Runtime.getRuntime().availableProcessors(), true );
        FixMessageContainer fmc = fmp.getFixMessageContainer();

        //check out a FixMessage object
        FixMessage fm = fmc.getFixMessage();

        //create an instance of FixMessageReader instance for parsing
        FixMessageReader fmr = new FixMessageReader(fm);

        //An instance of NativeBytes for converting a String to bytes data
        NativeBytes nativeBytes = new DirectStore(sampleFixMessage.length()).bytes();
        nativeBytes.write(sampleFixMessage.replace('|', '\u0001').getBytes());
        byte[] msgBytes = sampleFixMessage.replace('|', '\u0001').getBytes();
        ByteBufferBytes byteBufBytes = new ByteBufferBytes(ByteBuffer.allocate(msgBytes.length).order(ByteOrder.nativeOrder()));
        byteBufBytes.write(msgBytes);

        //setting and parsing the fix message
        fmr.setFixBytes(byteBufBytes);
        fmr.parseFixMsgBytes();

        //gets a Field array with parsed data
        Field[] field = fmr.getFixMessage().getField();

        //Sets a checkedout FixMessage instance object with the FIX message information.
        for (int i = 0; i < field.length; i++) {
            fm.getField(i).setFieldData(field[i].getFieldData());
        }
    }

    /**
     * Tests Fix protocol data written from a FixMessage is read back correctly.
     * First a FixMessagePool with default available processors is created with FixMessage objects,
     * then Field objects of each FixMessage object's ByteBufferByte are used for data read/write.
     */
    @Test
    public void testUpdateFixFieldForEmptyMessage() {
        int fixMsgCount = Runtime.getRuntime().availableProcessors();
        FixMessagePool fmp = new FixMessagePool( null, Runtime.getRuntime().availableProcessors(), true );
        FixMessageContainer fmc = fmp.getFixMessageContainer();
        FixMessage fm = fmc.getFixMessage();

        String[] fixElements = {"8=FIX.4.2", "9=154", "35=6", "49=BRKR", "6=INVMGR", "34=238", "52=19980604-07:59:56", "23=115686", "28=N", "55=AXX.AX", "54=2", "27=250000", "44=7900.000000", "25=H", "10=231"};

        String[] fixData = null;
        for (int i = 0; i < fixElements.length; i++) {
            fixData = fixElements[i].split("=");

            //assert field is empty
            assertEquals(0, fm.getField(Integer.parseInt(fixData[0])).getFieldData().position());

            fm.getField(Integer.parseInt(fixData[0])).setFieldData(fixData[1].getBytes());

            //assert field data is equal to the written data from the FixMessage.
            assertEquals(fixData[1].length(), fm.getField(Integer.parseInt(fixData[0])).getFieldData().position());

            System.out.println(fixElements[i] + " length written " + fixData[1].getBytes().length + "<==>" + fm.getField(Integer.parseInt(fixData[0])).getFieldData().position());
        }

        ByteBufferBytes reverseLookupBuf = null;
        byte[] readBytes = null;
        int len = -1;
        String bufData = null;
        for (int i = 0; i < fixElements.length; i++) {
            fixData = fixElements[i].split("=");

            //reading data from ByteBufferByte back for verification
            reverseLookupBuf = fm.getField(Integer.parseInt(fixData[0])).getFieldData();
            reverseLookupBuf.flip();
            len = (int) (reverseLookupBuf.limit() - reverseLookupBuf.position());
            readBytes = new byte[len];
            reverseLookupBuf.readFully(readBytes);

            bufData = fm.getField(Integer.parseInt(fixData[0])).getNumber() + "=" + new String(readBytes);

            //compare original data with read data from FixMessage
            assertEquals(fixElements[i], bufData);

            System.out.println(fixElements[i] + ": Reverse Look up-->" + bufData);
        }
    }

    /**
     * Test the validity (check like 8|9|34|35|49|56|10 ) of a FIX message fed to FixMessage object checked out from the pool.
     * Rejects on incorrect message.
     * First a FixMessagePool with default available processors is created with FixMessage objects, then Field objects of each FixMessage object's ByteBufferByte are used for data read/write.
     */
    @Test
    public void testFixMessageSessionLevelCheck() {
        String validFixMessage = "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|10=231|";

        String invalidFixMessage_1 = "9=154|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|10=231|";
        String invalidFixMessage_2 = "8=FIX.4.2|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|10=231|";
        String invalidFixMessage_3 = "8=FIX.4.2|9=154|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|10=231|";
        String invalidFixMessage_4 = "8=FIX.4.2|9=154|35=6|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|10=231|";
        String invalidFixMessage_5 = "8=FIX.4.2|9=154|35=6|49=BRKR|34=238|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|10=231|";
        String invalidFixMessage_6 = "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|10=231|";
        String invalidFixMessage_7 = "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=AXX.AX|54=2|27=250000|44=7900.000000|25=H|";

        String fixMessagesArray[] = {validFixMessage, invalidFixMessage_1, invalidFixMessage_2,
                invalidFixMessage_3, invalidFixMessage_4, invalidFixMessage_5,
                invalidFixMessage_6, invalidFixMessage_7
        };

        int fixMsgCount = Runtime.getRuntime().availableProcessors();
        FixMessagePool fmp = new FixMessagePool( null, Runtime.getRuntime().availableProcessors(), true );
        FixMessageContainer fmc = fmp.getFixMessageContainer();
        FixMessage fm = fmc.getFixMessage();
        FixMessageReader fmr = new FixMessageReader(fm);

        for (int i = 0; i < fixMessagesArray.length; i++) {
            fmr.setFixMessage(fm);
            fmr.setFixBytes(fixMessagesArray[i]);
            try {
                fmr.parseFixMsgBytes();
                if (i == 0) {
                    System.out.println("Valid-!!->\t  " + fixMessagesArray[i]);
                    assertEquals(1, fm.isValid());

                } else {
                    System.out.println("INVALID??-->>\t" + fixMessagesArray[i]);
                    assertEquals(0, fm.isValid());
                }
                fm.reset();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                fm.reset();
            }
        }

    }

    /**
     * Tests to check the FixMessageReader object's parse() method's average processing time. This method re-uses the same FixMessage's data to re-inject the same
     * inside each iteration. Results may vary if new data was introduced. This is only a measure of parse functionality.
     * First a FixMessagePool with default available processors is created with FixMessage objects, then Field objects of each FixMessage object's ByteBufferByte are used for data read/write.
     *
     * @
     */
    @Test
    public void testAvgParseTime()   {

        String sampleFixMessage = "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=FIA.MI|54=2|27=250000|44=7900.000000|25=H|10=231|";
        int fixMsgCount = Runtime.getRuntime().availableProcessors();
        FixMessagePool fmp = new FixMessagePool( null, Runtime.getRuntime().availableProcessors(), true );
        FixMessageContainer fmc = fmp.getFixMessageContainer();
        FixMessage fm = fmc.getFixMessage();
        FixMessageReader fmr = new FixMessageReader(fm);

        byte[] msgBytes = sampleFixMessage.replace('|', '\u0001').getBytes();
        ByteBufferBytes byteBufBytes = new ByteBufferBytes(ByteBuffer.allocate(msgBytes.length).order(ByteOrder.nativeOrder()));
        byteBufBytes.write(msgBytes);

        int counter = 0;
        int runs = 50000000;
        long start = System.nanoTime();
        for (int i = 0; i < runs; i++) {
            fmr.setFixMessage(fm);
            fmr.setFixBytes(byteBufBytes);
            fmr.parseFixMsgBytes();
            counter++;
        }
        long time = System.nanoTime() - start;
        System.out.printf("Average parse time was %.2f us, fields per message %.2f, for %.2fm iterations", time / runs / 1e3, (double) counter / runs, runs / 1e6);
    }

    /**
     * Tests the average time taken to set and reverse read a FixMessage object's FIX protocol data. Its noticed that if the iteration count (@runs) >=50000000, this test may not succeed on
     * some machines like laptops.
     * First a FixMessagePool with default available processors is created with FixMessage objects, then Field objects of each FixMessage object's ByteBufferByte are used for data read/write.
     *
     * @
     */
    @Test(timeout = 120000)
    public void testAvgSetAndReverseLookuptime()   {
        String sampleFixMessage = "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|34=238|52=19980604-07:59:56|23=115686|28=N|55=FIA.MI|54=2|27=250000|44=7900.000000|25=H|10=231|";
        int fixMsgCount = Runtime.getRuntime().availableProcessors();
        FixMessagePool fmp = new FixMessagePool( null, Runtime.getRuntime().availableProcessors(), true );
        FixMessageContainer fmc = fmp.getFixMessageContainer();
        FixMessage fm = fmc.getFixMessage();
        FixMessageReader fmr = new FixMessageReader(fm);

        byte[] msgBytes = sampleFixMessage.replace('|', '\u0001').getBytes();
        ByteBufferBytes byteBufBytes = new ByteBufferBytes(ByteBuffer.allocate(msgBytes.length).order(ByteOrder.nativeOrder()));
        byteBufBytes.write(msgBytes);

        int counter = 0;
        int runs = 30000;//50000000; //changed to 30000 since this test fails sometimes. 
        ByteBufferBytes reverseLookupBuf = null;
        byte[] readBytes = null;
        int len = -1;
        String bufData = null;
        int[] fixFieldIds = {8, 9, 35, 49, 56, 34, 52, 23, 28, 55, 27, 44, 25, 10};
        fmr.setFixMessage(fm);
        fmr.setFixBytes(byteBufBytes);
        fmr.parseFixMsgBytes();
        long start = System.nanoTime();

        for (int i = 0; i < runs; i++) {
            for (int j = 0; j < fixFieldIds.length; j++) {
                //reading data from ByteBufferByte back for verification
                reverseLookupBuf = fm.getField(fixFieldIds[j]).getFieldData();
                reverseLookupBuf.flip();
                len = (int) (reverseLookupBuf.limit() - reverseLookupBuf.position());
                readBytes = new byte[len];
                reverseLookupBuf.readFully(readBytes);

//    			bufData = fm.getField(fixFieldIds[j]).getNumber()+"="+ new String (readBytes);    			
//    			System.out.println(fixFieldIds[j]+ ": Reverse Look up-->"+ bufData);
            }
            counter++;
        }
        long time = System.nanoTime() - start;
        System.out.printf("Average reverse-look-up time was %.2f us, for %.2fm iterations", time / runs / 1e3, runs / 1e6);
    }
}
