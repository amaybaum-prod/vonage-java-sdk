/*
 *   Copyright 2020 Vonage
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

public abstract class BaseInsightRequest {
    protected final String number;
    protected String country;
    protected Boolean cnam;
    protected String ipAddress;

    protected BaseInsightRequest(String number) {
        if ((this.number = number) == null || number.length() < 2) {
            throw new IllegalStateException("Must provide a number for insight.");
        }
    }

    public String getNumber() {
        return number;
    }

    public String getCountry() {
        return country;
    }
}
