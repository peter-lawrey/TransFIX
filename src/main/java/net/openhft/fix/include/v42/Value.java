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
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Place holder for FIX Protocol FixMessage field value. This is currently also implemented using
 * ByteBufferBytes
 */
public class Value implements Externalizable {
    protected String description;
    protected String _enum;//predefined OR UserDefined

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getEnum() {
        return _enum;
    }

    public void setEnum(String value) {
        this._enum = value;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeUTF(description);
        out.writeUTF(_enum);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        description = in.readUTF();
        _enum = in.readUTF();

    }

}
