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
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import net.openhft.fix.transport.netty.codec.NettyFrameDecoder;
import net.openhft.fix.transport.netty.codec.NettyFrameHelper;

/**
 *
 */
public class NettyTestSupport {

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     */
    protected static class MessageBuilder {
        private ByteBuf m_buffer;

        /**
         * c-tor
         */
        public MessageBuilder() {
            m_buffer = Unpooled.buffer();
        }

        /**
         *
         * @param key
         * @param value
         */
        public MessageBuilder add(String key,String value) {
            m_buffer.writeBytes(key.getBytes());
            m_buffer.writeByte(NettyFrameHelper.BYTE_EQUALS);
            m_buffer.writeBytes(value.getBytes());
            m_buffer.writeByte(NettyFrameHelper.BYTE_SOH);

            return this;
        }

        /**
         *
         * @return
         */
        public ByteBuf build() {
            return m_buffer;
        }
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @return
     */
    protected static EmbeddedChannel newEmbeddedChannelWithDecoder() {
        return new EmbeddedChannel(new NettyFrameDecoder());
    }

    /**
     *
     * @return
     */
    protected static ByteBuf newLogonMessage() {
        return new MessageBuilder()
            .add("8", "FIX.4.2")
            .add("9", "69")
            .add("35", "A")
            .add("34", "1")
            .add("49", "TEST")
            .add("52", "20140102-11:55:47.746")
            .add("56", "EXEC")
            .add("98", "0")
            .add("108", "30")
            .add("141", "Y")
            .add("10", "151")
            .build();
    }

}
