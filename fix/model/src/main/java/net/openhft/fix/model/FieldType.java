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

package net.openhft.fix.model;

/**
 * @author Adam Rosenberger
 */
public enum FieldType {
    Length(DataType.Int),
    TagNum(DataType.Int),
    SeqNum(DataType.Int),
    NumInGroup(DataType.Int),
    DayOfMonth(DataType.Int),

    Qty(DataType.Float),
    Price(DataType.Float),
    PriceOffset(DataType.Float),
    Amt(DataType.Float),
    Percentage(DataType.Float),

    Boolean(DataType.Char),

    MultipleCharValue(DataType.String),
    MultipleStringValue(DataType.String),
    Country(DataType.String),
    Currency(DataType.String),
    Exchange(DataType.String),
    MonthYear(DataType.String),
    UTCTimestamp(DataType.String),
    UTCTimeOnly(DataType.String),
    UTCDateOnly(DataType.String),
    LocalMktDate(DataType.String),
    TZTimeOnly(DataType.String),
    TZTimestamp(DataType.String),
    Data(DataType.String),
    XMLData(DataType.String),
    Language(DataType.String),

    Tenor(DataType.Pattern),
    Reserved100Plus(DataType.Pattern),
    Reserved1000Plus(DataType.Pattern),
    Reserved4000Plus(DataType.Pattern);

    private final DataType dataType;

    private FieldType(DataType dataType) {
        this.dataType = dataType;
    }

    public final boolean isInt() {
        return dataType == DataType.Int;
    }

    public final boolean isFloat() {
        return dataType == DataType.Float;
    }

    public final boolean isChar() {
        return dataType == DataType.Char;
    }

    public final boolean isString() {
        return dataType == DataType.String;
    }

    public final boolean isPattern() {
        return dataType == DataType.Pattern;
    }
}