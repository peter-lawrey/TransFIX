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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lburgazzoli
 */
public class SettingsHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsHelper.class);

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param <T>
     */
    public static interface Converter<T> {
        public T convert(final String data);
    }

    /**
     *
     * @param <T>
     */
    public static final class MapConverter<T> implements Converter<T> {
        private T defval;
        private final Map<String,T> maps;

        public MapConverter() {
            this.maps = new HashMap<String,T>();
            this.defval = null;
        }

        public MapConverter<T> put(String key,T val) {
            maps.put(key,val);
            return this;
        }

        public MapConverter<T> def(T val) {
            this.defval = val;
            return this;
        }

        @Override
        public T convert(final String data) {
            return maps.containsKey(data) ? maps.get(data) : defval;
        }
    }

    /**
     *
     */
    public static final class DateConverter implements Converter<Date> {
        private final DateFormat formatter;

        /**
         * c-tor
         *
         * @param format
         */
        public DateConverter(String format) {
            this.formatter = new SimpleDateFormat(format);
        }

        @Override
        public Date convert(final String data) {
            try {
                return this.formatter.parse(data);
            } catch (ParseException e) {
                LOGGER.warn("ParseException",e);
            }

            return null;
        }
    }

    /**
     *
     */
    public static final class StringConverter implements Converter<String> {
        @Override
        public String convert(final String data) {
            return data;
        }
    }

    /**
     *
     */
    public static final class IntegerConverter implements Converter<Integer> {
        @Override
        public Integer convert(final String data) {
            return Integer.parseInt(data);
        }
    }

    /**
     *
     */
    public static final class DoubleConverter implements Converter<Double> {
        @Override
        public Double convert(final String data) {
            return Double.parseDouble(data);
        }
    }

    /**

     */
    public static final class AddressesConverter implements Converter<List<InetSocketAddress>> {
        @Override
        public List<InetSocketAddress> convert(final String data) {
            List<InetSocketAddress> addresses = new LinkedList<InetSocketAddress>();
            for(String address : data.split(",")) {
                String[] hp =  address.split(":",2);
                addresses.add(new InetSocketAddress(hp[0],Integer.parseInt(hp[1])));
            }

            return addresses;
        }
    }

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param <T>
     */
    public static final class Setting<T> {
        private final String settingKey;
        private final Converter<T> converter;

        /**
         * c-tor
         *
         * @param settingKey
         * @param converter
         */
        public Setting(final String settingKey,final Converter<T> converter) {
            this.settingKey = settingKey;
            this.converter = converter;
        }

        /**
         *
         * @param props
         * @return
         */
        public T get(Properties props) {
            T retval = null;
            String propval = props.getProperty(this.settingKey);
            if(propval != null && this.converter != null) {
                retval = this.converter.convert(propval);
            }

            return retval;
        }
    }
}
