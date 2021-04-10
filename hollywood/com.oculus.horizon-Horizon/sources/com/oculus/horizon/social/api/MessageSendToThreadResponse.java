package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class MessageSendToThreadResponse {
    public Message message;

    public static class Message {
        public String id;
    }
}
