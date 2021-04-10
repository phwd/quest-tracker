package com.oculus.horizon.service_media;

public enum LiveStreamingLocation {
    TIMELINE(1),
    GROUP(2),
    PAGE(3);
    
    public static final String INTENT_KEY = "location_selector";
    public final int value;

    /* access modifiers changed from: public */
    LiveStreamingLocation(int i) {
        this.value = i;
    }

    public static LiveStreamingLocation fromValue(int i) {
        LiveStreamingLocation[] values = values();
        for (LiveStreamingLocation liveStreamingLocation : values) {
            if (liveStreamingLocation.getValue() == i) {
                return liveStreamingLocation;
            }
        }
        throw new IllegalArgumentException("");
    }

    public int getValue() {
        return this.value;
    }
}
