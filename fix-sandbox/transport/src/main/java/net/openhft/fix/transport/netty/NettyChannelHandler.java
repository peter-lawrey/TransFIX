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
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lburgazzoli
 */
public class NettyChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyChannelHandler.class);

    /**
     * c-tor
     */
    public NettyChannelHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf data) throws Exception {
        try {
            LOGGER.debug("Received: {}",data.toString());
        } finally {
            data.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.warn("Unexpected exception from downstream.", cause);
        ctx.close();
    }
}
