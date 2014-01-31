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
package net.openhft.fix.transport.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import net.openhft.fix.transport.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lburgazzoli
 */
public class NettyStorageHandler extends ChannelDuplexHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(NettyStorageHandler.class);

    /**
     *
     */
    public static final class Inboud extends ChannelInboundHandlerAdapter {
        private final Storage storage;

        /**
         * c-tor
         *
         * @param storage
         */
        public Inboud(final Storage storage) {
            this.storage = storage;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if(msg instanceof ByteBuf) {
                //TODO: storage.in().store((ByteBuf)msg)
                LOGGER.debug("store-in");
            }

            ctx.fireChannelRead(msg);
        }
    }


    /**
     *
     */
    public static final class Outbound extends ChannelOutboundHandlerAdapter {
        private final Storage storage;

        /**
         * c-tor
         *
         * @param storage
         */
        public Outbound(final Storage storage) {
            this.storage = storage;
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            if(msg instanceof ByteBuf) {
                //TODO: storage.out().store((ByteBuf)msg)
                LOGGER.debug("store-out");
            }

            ctx.write(msg, promise);
        }
    }
}
