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
package com.vonage.client;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HttpConfigTest {
    private static final String EXPECTED_DEFAULT_API_BASE_URI = "https://api.nexmo.com";
    private static final String EXPECTED_DEFAULT_REST_BASE_URI = "https://rest.nexmo.com";
    private static final String EXPECTED_DEFAULT_SNS_BASE_URI = "https://sns.nexmo.com";

    @Test
    public void testDefaultFactoryMethod() {
        HttpConfig config = HttpConfig.defaultConfig();

        assertEquals(EXPECTED_DEFAULT_API_BASE_URI, config.getApiBaseUri());
        assertEquals(EXPECTED_DEFAULT_REST_BASE_URI, config.getRestBaseUri());
        assertEquals(EXPECTED_DEFAULT_SNS_BASE_URI, config.getSnsBaseUri());
    }

    @Test
    public void testApiBaseUriOnly() {
        HttpConfig config = HttpConfig.builder().apiBaseUri("https://example.com").build();

        assertEquals("https://example.com", config.getApiBaseUri());
        assertEquals(EXPECTED_DEFAULT_REST_BASE_URI, config.getRestBaseUri());
        assertEquals(EXPECTED_DEFAULT_SNS_BASE_URI, config.getSnsBaseUri());
    }

    @Test
    public void testApiRestUriOnly() {
        HttpConfig config = HttpConfig.builder().restBaseUri("https://example.com").build();

        assertEquals(EXPECTED_DEFAULT_API_BASE_URI, config.getApiBaseUri());
        assertEquals("https://example.com", config.getRestBaseUri());
        assertEquals(EXPECTED_DEFAULT_SNS_BASE_URI, config.getSnsBaseUri());
    }

    @Test
    public void testSnsBaseUriOnly() {
        HttpConfig config = HttpConfig.builder().snsBaseUri("https://example.com").build();

        assertEquals(EXPECTED_DEFAULT_API_BASE_URI, config.getApiBaseUri());
        assertEquals(EXPECTED_DEFAULT_REST_BASE_URI, config.getRestBaseUri());
        assertEquals("https://example.com", config.getSnsBaseUri());
    }

    @Test
    public void testAllBaseUri() {
        HttpConfig config = HttpConfig.builder().baseUri("https://example.com").build();

        assertEquals("https://example.com", config.getApiBaseUri());
        assertEquals("https://example.com", config.getRestBaseUri());
        assertEquals("https://example.com", config.getSnsBaseUri());
    }
}
