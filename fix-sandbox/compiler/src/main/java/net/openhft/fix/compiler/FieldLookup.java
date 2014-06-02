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

package net.openhft.fix.compiler;

import net.openhft.collections.HugeConfig;
import net.openhft.collections.HugeHashMap;
import net.openhft.collections.SampleValues;
import net.openhft.fix.model.FixField;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adam Rosenberger
 */
public class FieldLookup {
    //private static final Map<String, FixField> FIELD_LOOKUPS = new HashMap<String, FixField>();
	private static int count=100;
	private static final HugeConfig config = HugeConfig.SMALL.clone().setSegments(256).setSmallEntrySize(72).setCapacity(count);	
	private static final HugeHashMap<CharSequence, FixField> FIELD_LOOKUPS = new HugeHashMap<CharSequence, FixField>(config, CharSequence.class, FixField.class);
	
    static {
        FIELD_LOOKUPS.put("INT", FixField.Int);
        FIELD_LOOKUPS.put("LENGTH", FixField.Length);
        FIELD_LOOKUPS.put("TAGNUM", FixField.TagNum);
        FIELD_LOOKUPS.put("SEQNUM", FixField.SeqNum);
        FIELD_LOOKUPS.put("NUMINGROUP", FixField.NumInGroup);
        FIELD_LOOKUPS.put("DAYOFMONTH", FixField.DayOfMonth);
        FIELD_LOOKUPS.put("FLOAT", FixField.Float);
        FIELD_LOOKUPS.put("QTY", FixField.Qty);
        FIELD_LOOKUPS.put("QUANTITY", FixField.Qty);
        FIELD_LOOKUPS.put("PRICE", FixField.Price);
        FIELD_LOOKUPS.put("PRICEOFFSET", FixField.PriceOffset);
        FIELD_LOOKUPS.put("AMT", FixField.Amt);
        FIELD_LOOKUPS.put("PERCENTAGE", FixField.Percentage);
        FIELD_LOOKUPS.put("CHAR", FixField.Char);
        FIELD_LOOKUPS.put("BOOLEAN", FixField.Boolean);
        FIELD_LOOKUPS.put("STRING", FixField.String);
        FIELD_LOOKUPS.put("MULTIPLEVALUECHAR", FixField.MultipleCharValue);
        FIELD_LOOKUPS.put("MULTIPLECHARVALUE", FixField.MultipleCharValue);
        FIELD_LOOKUPS.put("MULTIPLESTRINGVALUE", FixField.MultipleStringValue);
        FIELD_LOOKUPS.put("MULTIPLEVALUESTRING", FixField.MultipleStringValue);
        FIELD_LOOKUPS.put("COUNTRY", FixField.Country);
        FIELD_LOOKUPS.put("CURRENCY", FixField.Currency);
        FIELD_LOOKUPS.put("EXCHANGE", FixField.Exchange);
        FIELD_LOOKUPS.put("MONTHYEAR", FixField.MonthYear);
        FIELD_LOOKUPS.put("UTCTIMESTAMP", FixField.UTCTimestamp);
        FIELD_LOOKUPS.put("UTCTIME", FixField.UTCTimeOnly);
        FIELD_LOOKUPS.put("UTCTIMEONLY", FixField.UTCTimeOnly);
        FIELD_LOOKUPS.put("UTCDATE", FixField.UTCDateOnly);
        FIELD_LOOKUPS.put("UTCDATEONLY", FixField.UTCDateOnly);
        FIELD_LOOKUPS.put("LOCALMKTDATE", FixField.LocalMktDate);
        FIELD_LOOKUPS.put("TZTIMEONLY", FixField.TZTimeOnly);
        FIELD_LOOKUPS.put("TZTIMESTAMP", FixField.TZTimestamp);
        FIELD_LOOKUPS.put("XMLDATA", FixField.XMLData);
        FIELD_LOOKUPS.put("DATA", FixField.Data);
        FIELD_LOOKUPS.put("LANGUAGE", FixField.Language);
        FIELD_LOOKUPS.put("TENOR", FixField.Tenor);
        FIELD_LOOKUPS.put("RESERVED100PLUS", FixField.Reserved100Plus);
        FIELD_LOOKUPS.put("RESERVED1000PLUS", FixField.Reserved1000Plus);
        FIELD_LOOKUPS.put("RESERVED4000PLUS", FixField.Reserved4000Plus);
    }

    public static FixField fieldFor(CharSequence xmlTag) {
        return FIELD_LOOKUPS.get(xmlTag);
    }

}
