package net.openhft.fix.include.v42;

import org.junit.Test;

import net.openhft.fix.include.util.FixMessagePool;
import net.openhft.fix.include.util.FixMessagePool.FixMessageContainer;

public class FIXMessageBuilderTest {

	@Test
    public void test() throws Exception {
        int fixMsgCount = 5;
		/*int SIZE = 128;    
	    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(SIZE);
        long addr = ((DirectBuffer) byteBuffer).address();
        NativeBytes bytes = new NativeBytes(addr, addr + SIZE);*/

        FixMessagePool fmp = new FIXMessageBuilder().initFixMessagePool(true, fixMsgCount);
        FixMessageContainer fmc = fmp.getFixMessageContainer();
        FixMessage fm = fmc.getFixMessage();
        //fm.getField(9).setFieldData(new DirectStore("121212121".length()).bytes());
        //bytes.writeInt(769876987);
        fm.getField(12).getFieldData().writeUTF("StringTestoeriupwouropweiur");
        //fm.getField(12).getFieldData().position(0);
        //fm.getField(12).getFieldData().flip();
        System.out.println("-->" + fm.getFixString());
        fmp.putFixMessageContainer(fmc);

    }
}
