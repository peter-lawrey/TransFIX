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
package net.openhft.fix.transport.storage;

import net.openhft.chronicle.IndexedChronicle;
import net.openhft.chronicle.tools.ChronicleTools;
import net.openhft.fix.transport.Context;
import net.openhft.fix.transport.Storage;

import java.io.IOException;

/**
 * @author lburgazzoli
 */
public class ChronicleStorage implements Storage {
    private final Context context;
    private IndexedChronicle chronicle;

    /**
     * c-tor
     *
     * @param context
     */
    public ChronicleStorage(final Context context) {
        this.context = context;
        this.chronicle = null;

        ChronicleTools.warmup();
    }

    @Override
    public void close() throws IOException {
        if(this.chronicle != null) {
            this.chronicle.close();
        }
    }
}
