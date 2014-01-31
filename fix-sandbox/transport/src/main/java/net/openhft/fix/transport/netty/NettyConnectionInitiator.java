/*
 * Copyright 2014 Peter Lawrey
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

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.openhft.fix.transport.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lburgazzoli
 */
public class NettyConnectionInitiator extends NettyConnection {

    private static Logger LOGGER = LoggerFactory.getLogger(NettyConnectionInitiator.class);

    // *************************************************************************
    //
    // *************************************************************************

    /**
     * The TransFIX's context.
     */
    private final Context context;

    /**
     * The Netty's Bootstrap.
     */
    private Bootstrap boot;

    /**
     *
     */
    private final AtomicInteger connectionAttempt;

    /**
     *
     */
    private final NettySettings settings;

    /**
     * c-tor
     *
     * @param context
     */
    public NettyConnectionInitiator(final Context context) {
        this.context = context;
        this.boot = null;
        this.connectionAttempt = new AtomicInteger(0);
        this.settings = new NettySettings(context.getSettings());
    }

    // *************************************************************************
    //
    // *************************************************************************

    @Override
    public void start() throws Exception {
        if(this.boot == null) {
            this.boot = new Bootstrap();
            this.boot.group(new NioEventLoopGroup());
            this.boot.channel(NioSocketChannel.class);
            this.boot.option(ChannelOption.SO_KEEPALIVE, settings.keepAlive());
            this.boot.option(ChannelOption.TCP_NODELAY, settings.tcpNodelay());

            if(settings.pooledAllocator()) {
                int nHeapArena = settings.nHeapArena();
                int nDirectArena = settings.nHeapArena();
                int pageSize = settings.pageSize();
                int maxOrder = settings.maxOrder();

                if(nHeapArena != -1 && nDirectArena != -1 && pageSize != -1 && maxOrder != -1) {
                    this.boot.option(
                        ChannelOption.ALLOCATOR,
                        new PooledByteBufAllocator(
                            settings.diretAllocator(),
                            nHeapArena,
                            nDirectArena,
                            pageSize,
                            maxOrder));
                } else {
                    this.boot.option(
                        ChannelOption.ALLOCATOR,
                        new PooledByteBufAllocator(settings.diretAllocator()));
                }
            }

            this.boot.handler(new NettyChannelInitializer());

            doConnect();
        } else {
            throw new RuntimeException("Connection is alive");
        }
    }

    @Override
    public void stop() throws Exception {
        Future<?> future = this.boot.group().shutdownGracefully();
        future.addListener(new GenericFutureListener<Future<Object>>() {
            @Override
            public void operationComplete(Future<Object> future) throws Exception {
                if (future.isDone() && future.isSuccess()) {
                    NettyConnectionInitiator.this.boot = null;
                    LOGGER.debug("Disconnected");
                } else if (!future.isSuccess() && !future.isCancelled()) {
                    LOGGER.warn("Error", future.cause());
                }
            }
        });
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     */
    private void doConnect() {
        if(this.boot != null) {
            //TODO: retrieve from settings
            InetSocketAddress addr = new InetSocketAddress("127.0.0.1",9876);
            this.boot.connect(addr).addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) {
                    if (future.isDone() && future.isSuccess()) {
                        connectionAttempt.set(0);
                        LOGGER.debug("Connected : {}",future.channel());
                    } else if (!future.isSuccess() && !future.isCancelled()) {
                        LOGGER.warn("Error", future.cause());
                        doReconnect();
                    }
                }
            });
        }
    }

    /**
     *
     */
    private void doReconnect() {
        if(this.boot != null) {
            if(connectionAttempt.incrementAndGet() < this.settings.getMaxReconnectAttempt()) {
                final Runnable task = new Runnable() {
                    public void run() {
                        doConnect();
                    }
                };

                this.boot.group().schedule(task,this.settings.getReconnectDelay(), TimeUnit.SECONDS);
            } else {
                LOGGER.warn("doReconnect");
            }
        }
    }
}
