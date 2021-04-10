package com.oculus.horizon.service_media;

public enum LiveStreamingMicrophoneStatus {
    MICROPHONE_ON(1),
    MICROPHONE_OFF(2);
    
    public static final String INTENT_KEY = "microphone_status";
    public final int value;

    /* access modifiers changed from: public */
    LiveStreamingMicrophoneStatus(int i) {
        this.value = i;
    }

    public static LiveStreamingMicrophoneStatus fromValue(int i) {
        LiveStreamingMicrophoneStatus[] values = values();
        for (LiveStreamingMicrophoneStatus liveStreamingMicrophoneStatus : values) {
            if (liveStreamingMicrophoneStatus.getValue() == i) {
                return liveStreamingMicrophoneStatus;
            }
        }
        throw new IllegalArgumentException("");
    }

    public int getValue() {
        return this.value;
    }
}
