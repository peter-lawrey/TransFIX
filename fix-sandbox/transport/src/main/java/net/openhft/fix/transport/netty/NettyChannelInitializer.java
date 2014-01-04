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

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import net.openhft.fix.transport.netty.codec.NettyFrameDecoder;
import net.openhft.fix.transport.netty.codec.NettyFrameEncoder;


/**
 * @author lburgazzoli
 */
public class NettyChannelInitializer extends ChannelInitializer {

    /**
     * c-tor
     */
    public NettyChannelInitializer() {
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast("decoder",new NettyFrameDecoder());
        channel.pipeline().addLast("encoder",new NettyFrameEncoder());
        //ch.pipeline().addLast("handler",new NettyChannelHandler());
    }
}
