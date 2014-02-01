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
package net.openhft.fix;

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

    public static final String KEY_SESSION_TYPE                  = "session.type";
    public static final String KEY_SESSION_ADDRESSES             = "session.addresses";
    public static final String KEY_SESSION_RECONNECT_DELAY       = "session.reconenct.delay";
    public static final String KEY_SESSION_RECONNECT_MAX_ATTEMPT = "session.reconenct.maxAttempt";
    public static final String KEY_TCP_NODELAY                   = "session.tcp.noDelay";
    public static final String KEY_TCP_KEEPALIVE                 = "session.tcp.keepAlive";

    // **********************************************************************
    //
    // *************************************************************************

    private final Properties properties;

    /**
     * c-tor
     */
    public Settings() {
        this.properties = new Properties();
    }

    /**
     * c-tor
     */
    public Settings(final Properties properties) {
        this.properties = new Properties();
        this.properties.putAll(properties);
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
     * @return
     */
    public Boolean getBoolean(String key) {
        return getBoolean(key, Boolean.FALSE);
    }

    /**

     * @param key
     * @param defval
     * @return
     */
    public Boolean getBoolean(String key, Boolean defval) {
        String val = get(key);

        if( StringUtils.equalsIgnoreCase("true",val) ||
            StringUtils.equalsIgnoreCase("yes" ,val) ||
            StringUtils.equalsIgnoreCase("0"   ,val) ) {
            return Boolean.TRUE;
        }

        if( StringUtils.equalsIgnoreCase("false",val) ||
            StringUtils.equalsIgnoreCase("no"   ,val) ||
            StringUtils.equalsIgnoreCase("0"    ,val) ) {
            return Boolean.FALSE;
        }

        return defval;
    }

    /**
     *
     * @param key
     * @return
     */
    public Integer getInteger(String key) {
        return getInteger(key,-1);
    }

    /**
     *
     * @param key
     * @param defval
     * @return
     */
    public Integer getInteger(String key,Integer defval) {
        String val = get(key);
        if(StringUtils.isNotBlank(val)) {
            return Integer.parseInt(val);
        }

        return defval;
    }

    /**
     *
     * @param key
     * @return
     */
    public Double getDouble(String key) {
        return getDouble(key, 0.0);
    }

    /**
     *
     * @param key
     * @param defval
     * @return
     */
    public Double getDouble(String key,Double defval) {
        String val = get(key);
        if(StringUtils.isNotBlank(val)) {
            return Double.parseDouble(val);
        }

        return defval;
    }
}
