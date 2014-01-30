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
public final class SettingsHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsHelper.class);

    // *************************************************************************
    //
    // *************************************************************************

    public static final SettingsTypeConverter<SessionType> CONVERTER_SESSION_TYPE =
        new MapConverter<SessionType>()
            .def(SessionType.UNKNOWN)
            .put("initiator", SessionType.INITIATOR)
            .put("acceptor", SessionType.ACCEPTOR);

    public static final SettingsTypeConverter<List<InetSocketAddress>> CONVERTER_ADDRESSES =
        new AddressesConverter();

    public static final SettingsTypeConverter<Integer> CONVERTER_INTEGER =
        new IntegerConverter();

    public static final SettingsTypeConverter<Double> CONVERTER_DOUBLE =
        new DoubleConverter();

    public static final SettingsTypeConverter<Boolean> CONVERTER_BOOLEAN =
        new MapConverter<Boolean>()
            .def(Boolean.FALSE)
            .put("true", Boolean.TRUE)
            .put("yes", Boolean.TRUE)
            .put("y", Boolean.TRUE)
            .put("1", Boolean.TRUE)
            .put("false", Boolean.FALSE)
            .put("no", Boolean.FALSE)
            .put("n", Boolean.FALSE)
            .put("0", Boolean.FALSE);

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @param <T>
     */
    public static class MapConverter<T> implements SettingsTypeConverter<T> {
        private T defval;
        private final Map<Object,T> maps;

        public MapConverter() {
            this.maps = new HashMap<Object,T>();
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
        public T convertTo(String value) {
            return maps.containsKey(value) ? maps.get(value) : defval;
        }
    }

    /**
     *
     */
    public static final class DateConverter implements SettingsTypeConverter<Date> {
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
        public Date convertTo(final String data) {
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
    public static final class StringConverter implements SettingsTypeConverter<String> {
        @Override
        public String convertTo(final String data) {
            return data;
        }
    }

    /**
     *
     */
    public static final class IntegerConverter implements SettingsTypeConverter<Integer> {
        @Override
        public Integer convertTo(final String data) {
            return Integer.parseInt(data);
        }
    }

    /**
     *
     */
    public static final class DoubleConverter implements SettingsTypeConverter<Double> {
        @Override
        public Double convertTo(final String data) {
            return Double.parseDouble(data);
        }
    }

    /**

     */
    public static final class AddressesConverter implements SettingsTypeConverter<List<InetSocketAddress>> {
        @Override
        public List<InetSocketAddress> convertTo(final String data) {
            List<InetSocketAddress> addresses = new LinkedList<InetSocketAddress>();
            for(String address : data.split(",")) {
                String[] hp =  address.split(":",2);
                addresses.add(new InetSocketAddress(hp[0],Integer.parseInt(hp[1])));
            }

            return addresses;
        }
    }
}
