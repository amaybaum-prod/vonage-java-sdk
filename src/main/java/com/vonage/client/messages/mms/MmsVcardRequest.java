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
package com.vonage.client.messages.mms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vonage.client.messages.MessageType;
import com.vonage.client.messages.internal.MessagePayload;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public final class MmsVcardRequest extends MmsRequest {

	MmsVcardRequest(Builder builder) {
		super(builder, MessageType.VCARD);
		payload = new MessagePayload(builder.url);
		payload.validateUrlExtension("vcf");
	}

	@JsonProperty("vcard")
	public MessagePayload getVcard() {
		return payload;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends MmsRequest.Builder<MmsVcardRequest, Builder> {
		Builder() {}

		/**
		 * (REQUIRED)
		 * Sets the URL of the vCard attachment. Supports only {@code .vcf} file extension.
		 *
		 * @param url The URL as a string.
		 * @return This builder.
		 */
		public Builder url(String url) {
			return super.url(url);
		}

		@Override
		public MmsVcardRequest build() {
			return new MmsVcardRequest(this);
		}
	}

}
