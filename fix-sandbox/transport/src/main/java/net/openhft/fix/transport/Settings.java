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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author lburgazzoli
 *
 * TODO: re-enginering
 */
public class Settings {
    private static final Logger LOGGER = LoggerFactory.getLogger(Settings.class);

    public static final String SESSION_TYPE      = "session.type";
    public static final String SESSION_ADDRESSES = "session.addresses";

    // **********************************************************************
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
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return properties.getProperty(key);
    }

    /**
     *
     * @param key
     * @param converter
     * @param <T>
     * @return
     */
    public <T> T get(String key,SettingsTypeConverter<T> converter) {
        String propval = properties.getProperty(key);
        if(StringUtils.isNoneBlank(propval) && converter != null) {
            return converter.convertTo(propval);
        }

        return null;
    }
}
