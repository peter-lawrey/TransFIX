/*
 * Copyright 2013 peter.lawrey Lawrey
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

import net.openhft.fix.model.FixField;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class is used for
 *
 * @author Adam Rosenberger/Anshul Shelley
 */
public class FieldLookup {
    private static final Comparator<CharSequence> charSequenceIgnoreCaseComparator =
            new Comparator<CharSequence>() {
        @Override
        public int compare(CharSequence o1, CharSequence o2) {
            int len1 = o1.length();
            int len2 = o2.length();
            int n = Math.min(len1, len2);
            for (int i = 0; i < n; i++) {
                char c1 = Character.toLowerCase(o1.charAt(i));
                char c2 = Character.toLowerCase(o2.charAt(i));
                if (c1 != c2)
                    return c1 - c2;
            }
            return len1 - len2;
        }
    };

    private static final Map<CharSequence, FixField> FIELD_LOOKUPS =
            new TreeMap<>(charSequenceIgnoreCaseComparator);

    static {
        FIELD_LOOKUPS.put("INT", FixField.Int);
        FIELD_LOOKUPS.put("LENGTH", FixField.Length);
        FIELD_LOOKUPS.put("TAGNUM", FixField.TagNum);
        FIELD_LOOKUPS.put("SEQNUM", FixField.SeqNum);
        FIELD_LOOKUPS.put("NUMINGROUP", FixField.NumInGroup);
        FIELD_LOOKUPS.put("DAYOFMONTH", FixField.DayOfMonth);
        FIELD_LOOKUPS.put("FLOAT", FixField.Double);
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

    /**
     * Looks up and returns appropriate FixField as per 4.2 spec.
     *
     * @param xmlTag - published 4.2 data type.
     * @return - FixField
     */
    public static FixField fieldFor(CharSequence xmlTag) {
        return FIELD_LOOKUPS.get(xmlTag);
    }
}
