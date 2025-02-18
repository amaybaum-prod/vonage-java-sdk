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
package com.vonage.client.voice.ncco;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a VBC endpoint used in a {@link ConnectAction}. See
 * <a href=https://developer.vonage.com/voice/voice-api/ncco-reference#vbc-endpoint>the documentation</a>
 * for an example.
 *
 * @since 7.3.0
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class VbcEndpoint implements Endpoint {
    private static final String TYPE = "app";

    private final String extension;

    private VbcEndpoint(Builder builder) {
        this.extension = builder.extension;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public String getExtension() {
        return extension;
    }

    public static Builder builder(String user) {
        return new Builder(user);
    }

    public static class Builder {
        private String extension;

        Builder(String extension) {
            this.extension = extension;
        }

        public Builder extension(String extension) {
            this.extension = extension;
            return this;
        }

        public VbcEndpoint build() {
            return new VbcEndpoint(this);
        }
    }
}