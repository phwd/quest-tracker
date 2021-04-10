package com.oculus.horizon.social.request;

public enum PartyMicrophoneChannel {
    APP("APP"),
    MUTE("MUTE"),
    PARTY("PARTY");
    
    public final String mValue;

    public String toString() {
        return this.mValue;
    }

    /* access modifiers changed from: public */
    PartyMicrophoneChannel(String str) {
        this.mValue = str;
    }
}
