/*
 * Copyright 2013 Peter Lawrey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.openhft.fix.transport.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import net.openhft.fix.transport.netty.codec.NettyFrameDecoder;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class NettyFrameDecoderTest extends NettyTestSupport {

    // *************************************************************************
    // DECODER
    // *************************************************************************

    @Test
    public void testDecode() {
        ByteBuf         message = newLogonMessage();
        ByteBuf         msgcopy = message.copy();
        EmbeddedChannel channel = new EmbeddedChannel(new NettyFrameDecoder());

        // raw write write
        Assert.assertTrue(channel.writeInbound(message));
        Assert.assertTrue(channel.finish());

        // read
        byte[] result = (byte[])channel.readInbound();
        Assert.assertNotNull(result);

        if(msgcopy.hasArray()) {
            Assert.assertArrayEquals(msgcopy.array(),result);
        }
    }

    @Test
    public void testDecodeMultiple() {
        EmbeddedChannel channel = new EmbeddedChannel(new NettyFrameDecoder());

        // raw write write
        Assert.assertTrue(channel.writeInbound(newLogonMessage().writeBytes(newLogonMessage())));
        Assert.assertTrue(channel.finish());

        // read
        Assert.assertNotNull(channel.readInbound());
        Assert.assertNotNull(channel.readInbound());
        Assert.assertNull(channel.readInbound());
    }

    @Test
    public void testDecodeMultipleWithPartialMessages() {

        ByteBuf pmessage  = newLogonMessage();
        ByteBuf pmessage1 = pmessage.readBytes(10);
        ByteBuf pmessage2 = pmessage.readBytes(pmessage.readableBytes());

        EmbeddedChannel channel  = new EmbeddedChannel(new NettyFrameDecoder());

        // raw write:
        // - 1 complete message
        // - 1 partial message
        Assert.assertTrue(channel.writeInbound(newLogonMessage().writeBytes(pmessage1)));

        // read
        Assert.assertNotNull(channel.readInbound());
        Assert.assertNull(channel.readInbound());

        // raw write:
        // - 1 partial message
        // - 1 compete message
        Assert.assertTrue(channel.writeInbound(pmessage2.writeBytes(newLogonMessage())));
        Assert.assertTrue(channel.finish());

        // read
        byte[] result1 = (byte[])channel.readInbound();
        Assert.assertNotNull(result1);

        byte[] result2 = (byte[])channel.readInbound();
        Assert.assertNotNull(result2);

        Assert.assertNull(channel.readInbound());
    }
}
