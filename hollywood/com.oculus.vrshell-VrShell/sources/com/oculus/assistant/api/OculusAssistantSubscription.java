package com.oculus.assistant.api;

import javax.annotation.Nullable;

public class OculusAssistantSubscription {
    private String mMessageType;
    private IOculusAssistantSubscriber mSubscriber;
    @Nullable
    private String mSubscriberKey;

    public OculusAssistantSubscription(String str, IOculusAssistantSubscriber iOculusAssistantSubscriber, String str2) {
        this.mMessageType = str;
        this.mSubscriber = iOculusAssistantSubscriber;
        this.mSubscriberKey = str2;
    }

    public String getMessageType() {
        return this.mMessageType;
    }

    public IOculusAssistantSubscriber getSubscriber() {
        return this.mSubscriber;
    }

    public String getSubscriberKey() {
        return this.mSubscriberKey;
    }

    public void setSubscriberKey(String str) {
        this.mSubscriberKey = str;
    }
}
