package com.oculus.video.analytics;

/* access modifiers changed from: package-private */
/* compiled from: VideoAnalyticsSession */
public enum VideoSessionState {
    INIT("init"),
    PLAYING("playing"),
    SEEKING("seeking"),
    PAUSED("paused"),
    LOADING("loading"),
    SESSION_END("session_end"),
    RESUMING("resuming");
    
    final String value;

    private VideoSessionState(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
