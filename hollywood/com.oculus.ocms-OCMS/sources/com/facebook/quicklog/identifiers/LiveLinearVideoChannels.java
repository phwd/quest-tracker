package com.facebook.quicklog.identifiers;

public class LiveLinearVideoChannels {
    public static final int BROADCAST_TRANSITION_ANDROID_IOS = 33554433;
    public static final short MODULE_ID = 512;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "LIVE_LINEAR_VIDEO_CHANNELS_BROADCAST_TRANSITION_ANDROID_IOS";
    }
}
