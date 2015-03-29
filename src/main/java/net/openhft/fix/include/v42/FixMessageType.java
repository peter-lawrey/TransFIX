/*
 * Copyright 2015 peter.lawrey 
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
package net.openhft.fix.include.v42;

import java.io.Externalizable;

/**
 * A FIX message should adhere to this type
 */
public interface FixMessageType extends Externalizable {

    /**
     * @param <T>- type param
     * @return- Type param
     */
    <T> T getField();

    /**
     * @param <T>- type param
     * @param fieldSize
     * @return-Type param
     */
    <T> T setFieldSize(int fieldSize);

    /**
     * @param <T>- type param
     * @param valueSize
     * @return Type param
     */
    <T> T setValueSize(int valueSize);

    /**
     * @param <T>- type param
     * @return Type param
     */
    <T> T getMessage();

    /**
     * @param <T>- type param
     * @param messageSize
     * @return Type param
     */
    <T> T setMessagesSize(int messageSize);

    /**
     * @param <T>- type param
     * @param groupSize
     * @return Type param
     */
    <T> T setGroupSize(int groupSize);


}
