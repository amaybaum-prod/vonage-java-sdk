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
package com.vonage.client.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import static com.vonage.client.messages.MessageType.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * Represents the services available for sending messages.
 */
public enum Channel {
	SMS (TEXT),
	MMS (TEXT, IMAGE, VCARD, AUDIO, VIDEO),
	WHATSAPP (TEXT, IMAGE, AUDIO, VIDEO, FILE, TEMPLATE, CUSTOM, LOCATION, STICKER, ORDER, REPLY, UNSUPPORTED),
	MESSENGER (TEXT, IMAGE, AUDIO, VIDEO, FILE, UNSUPPORTED),
	VIBER (TEXT, IMAGE, VIDEO, FILE);

	private final Set<MessageType> supportedTypes;

	Channel(MessageType type1, MessageType... additionalTypes) {
		this.supportedTypes = EnumSet.of(type1, additionalTypes);
	}

	/**
	 * This method is useful for determining which message types are supported
	 * by this messaging service.
	 *
	 * @return The Set of message types that this service can handle.
	 */
	public Set<MessageType> getSupportedMessageTypes() {
		return supportedTypes;
	}

	@JsonCreator
	public static Channel fromString(String value) {
		if (value == null) return null;
		return Channel.valueOf(value.toUpperCase());
	}

	@JsonValue
	@Override
	public String toString() {
		if (this == VIBER) {
			return "viber_service";
		}
		return name().toLowerCase();
	}
}
