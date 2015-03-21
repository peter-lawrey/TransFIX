package net.openhft.fix.include.v42;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.junit.Test;

import net.openhft.fix.include.util.FixMessagePool;
import net.openhft.fix.include.util.FixMessagePool.FixMessageContainer;
import net.openhft.lang.io.ByteBufferBytes;
import net.openhft.lang.io.DirectStore;
import net.openhft.lang.io.NativeBytes;

public class FixMessageReaderTest {

	@Test
    public void test() throws Exception {
        String sampleFixMessage = "8=FIX.4.2|9=154|35=6|49=BRKR|56=INVMGR|34=238|" +
                "52=19980604-07:59:56|23=115686|28=N|55=FIA.MI|54=2|27=250000|" +
                "44=7900.000000|25=H|10=231|";
        int fixMsgCount = Runtime.getRuntime().availableProcessors();
        FixMessagePool fmp = new FIXMessageBuilder().initFixMessagePool(true, fixMsgCount);
        FixMessageContainer fmc = fmp.getFixMessageContainer();
        FixMessage fm = fmc.getFixMessage();
        FixMessageReader fmr = new FixMessageReader(fm);
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
        byte[] msgBytes = sampleFixMessage.replace('|', '\u0001').getBytes();
        ByteBufferBytes byteBufBytes = new ByteBufferBytes(ByteBuffer.allocate(msgBytes.length)
                .order(ByteOrder.nativeOrder()));
        byteBufBytes.write(msgBytes);

        int counter = 0;
        int runs = 300000;
        long start = System.nanoTime();
        for (int i = 0; i < runs; i++) {
            fmr.setFixBytes(byteBufBytes);
            fmr.parseFixMsgBytes();
            counter++;
        }
        long time = System.nanoTime() - start;
        System.out.printf("Average parse time was %.2f us, fields per message %.2f%n",
                time / runs / 1e3, (double) counter / runs);

    }
}
