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
package com.vonage.client.insight;

import com.vonage.client.AbstractMethod;
import com.vonage.client.HttpWrapper;
import com.vonage.client.auth.SignatureAuthMethod;
import com.vonage.client.auth.TokenAuthMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

class StandardInsightEndpoint extends AbstractMethod<StandardInsightRequest, StandardInsightResponse> {
    private static final Class<?>[] ALLOWED_AUTH_METHODS = {SignatureAuthMethod.class, TokenAuthMethod.class};
    private static final String PATH = "/ni/standard/json";

    StandardInsightEndpoint(HttpWrapper httpWrapper) {
        super(httpWrapper);
    }

    @Override
    protected Class<?>[] getAcceptableAuthMethods() {
        return ALLOWED_AUTH_METHODS;
    }

    @Override
    public RequestBuilder makeRequest(StandardInsightRequest request) throws UnsupportedEncodingException {
        String uri = httpWrapper.getHttpConfig().getApiBaseUri() + PATH;
        RequestBuilder requestBuilder = RequestBuilder.post(uri)
                .setHeader("Accept", "application/json")
                .addParameter("number", request.getNumber());

        if (request.getCountry() != null) {
            requestBuilder.addParameter("country", request.getCountry());
        }
        if (request.getCnam() != null) {
            requestBuilder.addParameter("cnam", request.getCnam().toString());
        }
        return requestBuilder;
    }

    @Override
    public StandardInsightResponse parseResponse(HttpResponse response) throws IOException {
        return StandardInsightResponse.fromJson(basicResponseHandler.handleResponse(response));
    }
}
