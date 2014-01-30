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

import net.openhft.fix.transport.Connection;
import net.openhft.fix.transport.Context;
import net.openhft.fix.transport.Session;
import net.openhft.fix.transport.Settings;

/**
 * @author lburgazzoli
 */
public class NettySession implements Session {

    private final Context context;
    private Connection connection;

    /**
     * c-tor
     *
     * @param context
     */
    public NettySession(final Context context) {
        this.connection = null;
        this.context = context;
    }

    @Override
    public void start() throws Exception {
        if(this.connection == null) {
            this.connection = createConenction();
            this.connection.start();
        }
    }

    @Override
    public void stop() throws Exception {
        if(this.connection != null) {
            this.connection.stop();
            this.connection = null;
        }
    }

    /**
     * @return
     */
    protected Context getContext() {
        return this.context;
    }

    /**
     * @return
     */
    protected Connection createConenction() {
        return null;
    }
}
