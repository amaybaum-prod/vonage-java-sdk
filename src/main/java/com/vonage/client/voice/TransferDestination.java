/*
 *   Copyright 2023 Vonage
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.vonage.client.voice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.vonage.client.voice.ncco.Ncco;

/**
 * @deprecated Will be made package-private in next major release.
 */
@Deprecated
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TransferDestination {
    private final Type type;
    private final String[] urls;
    private final Ncco ncco;

    TransferDestination(String url) {
        this(Type.NCCO, url, null);
    }

    TransferDestination(Ncco ncco) {
        this(Type.NCCO, null, ncco);
    }

    public TransferDestination(Type type, String url) {
        this(type, url, null);
    }

    public TransferDestination(Type type, String url, Ncco ncco) {
        this.type = type;
        this.urls = url != null ? new String[]{url} : null;
        this.ncco = ncco;
    }

    @JsonProperty("type")
    public Type getType() {
        return type;
    }

    @JsonProperty("url")
    public String[] getUrls() {
        return urls;
    }

    @JsonProperty("ncco")
    public Ncco getNcco() {
        return ncco;
    }

    enum Type {
        NCCO;

        @JsonValue
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
}
