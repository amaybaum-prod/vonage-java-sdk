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
package com.vonage.client.auth;

import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TokenAuthMethodTest {
    @Test
    public void testAddingApiKeyAndSecretToJson() throws Exception {
        AuthMethod auth = new TokenAuthMethod("apikey", "secret");
        String before = "{\"name\":\"app name\",\"type\":\"voice\",\"answer_url\":\"https://example.com/answer\",\"event_url\":\"https://example.com/event\"}";
        RequestBuilder requestBuilder = RequestBuilder.get().setEntity(new StringEntity(before, ContentType.APPLICATION_JSON));

        RequestBuilder requestBuilderWithAuthentication = auth.applyAsJsonProperties(requestBuilder);

        String after = "{\"name\":\"app name\",\"type\":\"voice\",\"answer_url\":\"https://example.com/answer\",\"event_url\":\"https://example.com/event\",\"api_key\":\"apikey\",\"api_secret\":\"secret\"}";
        assertEquals(after, EntityUtils.toString(requestBuilderWithAuthentication.getEntity()));
    }
}
