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
package net.openhft.fix.include.v42;

import java.io.Externalizable;

/**
 * Interface necessary to implement a FixMessage object
 */
public interface FixMessageInterface extends Externalizable {
    /**
     * @return - int value of FIX major version
     */
    int getMajor();

    /**
     * @return - int value of FIX minor version
     */
    int getMinor();

    /**
     * @return - int value of FIX service pack
     */
    int getServicepack();

    /**
     * @return - CharSequence of this FIX message type
     */
    CharSequence getType();
}
