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
package net.openhft.fix.transport.examples;

import net.openhft.fix.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author lburgazzoli
 */
public class SimpleClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClient.class);

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @return
     */
    private static Settings getSettings() {
        Properties properties = new Properties();

        return new Settings(properties);
    }

    // *************************************************************************
    //
    // *************************************************************************

    public static void main(String[] args) {
        try {
            //TODO: add sample code
        } catch(Exception e) {
            LOGGER.warn("Exception",e);
        }
    }
}
