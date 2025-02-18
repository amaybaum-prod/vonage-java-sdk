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

import com.vonage.client.auth.AuthCollection;
import com.vonage.client.auth.AuthMethod;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import java.nio.charset.StandardCharsets;

/**
 * Internal class that holds available authentication methods and a shared HttpClient.
 */
public class HttpWrapper {
    private static final String CLIENT_NAME = "vonage-java-sdk";
    private static final String CLIENT_VERSION = "7.3.0";
    private static final String JAVA_VERSION = System.getProperty("java.version");
    private static final String USER_AGENT = String.format("%s/%s java/%s", CLIENT_NAME, CLIENT_VERSION, JAVA_VERSION);

    private AuthCollection authCollection;
    private HttpClient httpClient;
    private HttpConfig httpConfig;

    public HttpWrapper(AuthCollection authCollection) {
        this(HttpConfig.builder().build(), authCollection);
    }

    public HttpWrapper(HttpConfig httpConfig, AuthCollection authCollection) {
        this.authCollection = authCollection;
        this.httpConfig = httpConfig;
    }

    public HttpWrapper(AuthMethod... authMethods) {
        this(HttpConfig.builder().build(), authMethods);
    }

    public HttpWrapper(HttpConfig httpConfig, AuthMethod... authMethods) {
        this(new AuthCollection(authMethods));
        this.httpConfig = httpConfig;
    }

    public HttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = createHttpClient();
        }
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public AuthCollection getAuthCollection() {
        return authCollection;
    }

    public void setAuthCollection(AuthCollection authCollection) {
        this.authCollection = authCollection;
    }

    protected HttpClient createHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(200);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultConnectionConfig(
            ConnectionConfig.custom()
                .setCharset(StandardCharsets.UTF_8)
                .build()
        );
        connectionManager.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());

        // Need to work out a good value for the following:
        // threadSafeClientConnManager.setValidateAfterInactivity();

        RequestConfig requestConfig = RequestConfig.custom().build();

        return HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setUserAgent(USER_AGENT)
                .setDefaultRequestConfig(requestConfig)
                .useSystemProperties()
                .build();
    }

    public HttpConfig getHttpConfig() {
        return httpConfig;
    }

    public String getUserAgent() {
        return USER_AGENT;
    }
}
