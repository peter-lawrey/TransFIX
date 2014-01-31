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

import net.openhft.fix.transport.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lburgazzoli
 */
public class NettySettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettySettings.class);

    public static final String KEY_NETTY_TRACE                  = "session.netty.trace";
    public static final String KEY_NETTY_ALLOCATOR_POOLED       = "session.netty.allocator.pooled";
    public static final String KEY_NETTY_ALLOCATOR_DIRECT       = "session.netty.allocator.direct";
    public static final String KEY_NETTY_ALLOCATOR_NHEAPARENA   = "session.netty.allocator.nHeapArena";
    public static final String KEY_NETTY_ALLOCATOR_NDIRECTARENA = "session.netty.allocator.nDirectArena";
    public static final String KEY_NETTY_ALLOCATOR_PAGESIZE     = "session.netty.allocator.pageSize";
    public static final String KEY_NETTY_ALLOCATOR_MAXORDERS    = "session.netty.allocator.maxOrder";

    // *************************************************************************
    //
    // *************************************************************************

    private final Settings settings;

    /**
     * c-tor
     *
     * @param settings
     */
    public NettySettings(final Settings settings) {
        this.settings = settings;
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @return
     */
    public boolean trace() {
        return this.settings.getBoolean(KEY_NETTY_TRACE,Boolean.FALSE);
    }

    /**
     *
     * @return
     */
    public boolean tcpNodelay() {
        return this.settings.getBoolean(Settings.KEY_TCP_NODELAY,Boolean.TRUE);
    }

    /**
     *
     * @return
     */
    public boolean keepAlive() {
        return this.settings.getBoolean(Settings.KEY_TCP_KEEPALIVE,Boolean.TRUE);
    }

    /**
     *
     * @return
     */
    public boolean pooledAllocator() {
        return this.settings.getBoolean(KEY_NETTY_ALLOCATOR_POOLED,Boolean.TRUE);
    }

    /**
     *
     * @return
     */
    public boolean diretAllocator() {
        return this.settings.getBoolean(KEY_NETTY_ALLOCATOR_DIRECT,Boolean.TRUE);
    }

    /**
     *
     * @return
     */
    public int nHeapArena() {
        return this.settings.getInteger(KEY_NETTY_ALLOCATOR_NHEAPARENA, -1);
    }

    /**
     *
     * @return
     */
    public int nDirectArena() {
        return this.settings.getInteger(KEY_NETTY_ALLOCATOR_NDIRECTARENA, -1);
    }

    /**
     *
     * @return
     */
    public int pageSize() {
        return this.settings.getInteger(KEY_NETTY_ALLOCATOR_PAGESIZE, -1);
    }

    /**
     *
     * @return
     */
    public int maxOrder() {
        return this.settings.getInteger(KEY_NETTY_ALLOCATOR_MAXORDERS, -1);
    }

    /**
     *
     */
    public int getReconnectDelay() {
        //TODO: defaults
        return this.settings.getInteger(Settings.KEY_SESSION_RECONNECT_DELAY,5);
    }

    /**
     *
     */
    public int getMaxReconnectAttempt() {
        //TODO: defaults
        return this.settings.getInteger(Settings.KEY_SESSION_RECONNECT_MAX_ATTEMPT,5);
    }
}
