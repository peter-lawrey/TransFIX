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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Properties;

/**
 * @author lburgazzoli
 *
 * TODO: re-enginering
 */
public class Settings {
    private static final Logger LOGGER = LoggerFactory.getLogger(Settings.class);

    // *************************************************************************
    //
    // *************************************************************************

    public static final SettingsHelper.Setting<SessionType> SESSION_TYPE =
        new SettingsHelper.Setting<SessionType>(
            "session.type",
            new SettingsHelper.MapConverter<SessionType>()
                .def(SessionType.UNKNOWN)
                .put("initiator",SessionType.INITIATOR)
                .put("acceptor" ,SessionType.ACCEPTOR)
    );

    public static final SettingsHelper.Setting<List<InetSocketAddress>> SESSION_ADDRESSES =
        new SettingsHelper.Setting<List<InetSocketAddress>>(
            "session.addresses",
            new SettingsHelper.AddressesConverter()
    );

    // *************************************************************************
    //
    // *************************************************************************

    private Properties properties;

    /**
     * c-tor
     */
    public Settings() {
        properties = new Properties();
    }

    /**
     * @return the session type
     */
    public SessionType getSessionType() {
        return SESSION_TYPE.get(properties);
    }

    /**
     * @return the addresses
     */
    public List<InetSocketAddress> getAddresses() {
        return SESSION_ADDRESSES.get(properties);
    }
}
