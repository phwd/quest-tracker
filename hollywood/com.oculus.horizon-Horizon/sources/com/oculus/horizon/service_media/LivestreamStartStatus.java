package com.oculus.horizon.service_media;

public enum LivestreamStartStatus {
    SUCCESS(1),
    NO_PACKAGE_SET(-1),
    NO_FB_CONNECT(-2),
    NO_SESSION_ID(-3),
    MISSING_PARAMETERS(-4);
    
    public final int value;

    /* access modifiers changed from: public */
    LivestreamStartStatus(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
