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
package net.openhft.fix.transport;

import java.net.InetSocketAddress;
import java.util.*;

/**
 * @author lburgazzoli
 *
 * TODO: re-enginering
 */
public class Settings {
    public static final Setting<SessionType> SESSION_TYPE =
        new Setting<SessionType>("session.type",SessionType.UNKNOWN)
            .add("initiator",SessionType.INITIATOR)
            .add("acceptor" ,SessionType.ACCEPTOR);

    private Properties data;

    /**
     * c-tor
     */
    public Settings() {
        data = new Properties();
    }

    /**
     * @return the session type
     */
    public SessionType getSessionType() {
        return SESSION_TYPE.get(data);
    }

    /**
     * @return the addresses
     */
    public List<InetSocketAddress> getAddresses() {
        return new LinkedList<InetSocketAddress>();
    }

    // *************************************************************************
    //
    // *************************************************************************

    private static final class Setting<T> {
        private final String settingKey;
        private final T defval;
        private Map<String,T> dataMap;

        /**
         * c-tor
         *
         * @param settingKey
         */
        public Setting(String settingKey) {
           this(settingKey,null);
        }

        /**
         * c-tor
         *
         * @param settingKey
         * @param defval
         */
        public Setting(String settingKey,T defval) {
            this.dataMap = new HashMap<String,T>();
            this.settingKey = settingKey;
            this.defval = defval;
        }

        /**
         *
         * @param key
         * @param value
         * @return
         */
        public Setting<T> add(String key,T value) {
            dataMap.put(key,value);
            return this;
        }

        /**
         *
         * @param props
         * @return
         */
        public T get(Properties props) {
            T retval = defval;
            String val = props.getProperty(this.settingKey);
            if(val != null) {
                if(dataMap.containsKey(val)) {
                    retval = dataMap.get(val);
                }
            }

            return retval;
        }
    }
}
