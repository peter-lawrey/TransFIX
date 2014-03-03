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
        EmbeddedChannel channel = newEmbeddedChannelWithDecoder();

        // raw write write
        Assert.assertTrue(channel.writeInbound(message));
        Assert.assertTrue(channel.finish());

        // read
        ByteBuf result = (ByteBuf)channel.readInbound();
        Assert.assertNotNull(result);

        Assert.assertEquals(msgcopy,result);
    }

    @Test
    public void testDecodeMultiple() {
        EmbeddedChannel channel = newEmbeddedChannelWithDecoder();

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
        ByteBuf pmessager = pmessage.copy();
        ByteBuf pmessage1 = pmessage.readBytes(10);
        ByteBuf pmessage2 = pmessage.readBytes(pmessage.readableBytes());

        EmbeddedChannel channel  = newEmbeddedChannelWithDecoder();

        // raw write:
        // - 1 complete message (A)
        // - 1 partial message  (B)
        Assert.assertTrue(channel.writeInbound(newLogonMessage().writeBytes(pmessage1)));

        // read
        Assert.assertNotNull(channel.readInbound()); // A
        Assert.assertNull(channel.readInbound());

        // raw write:
        // - 1 partial message  (B)
        // - 1 complete message (C)
        Assert.assertTrue(channel.writeInbound(pmessage2.writeBytes(newLogonMessage())));
        Assert.assertTrue(channel.finish());

        // read
        ByteBuf result1 = (ByteBuf)channel.readInbound(); // B
        Assert.assertNotNull(result1);
        Assert.assertEquals(pmessager,result1);

        ByteBuf result2 = (ByteBuf)channel.readInbound(); //C
        Assert.assertNotNull(result2);
        Assert.assertEquals(pmessager,result2);

        Assert.assertNull(channel.readInbound());
    }
}
