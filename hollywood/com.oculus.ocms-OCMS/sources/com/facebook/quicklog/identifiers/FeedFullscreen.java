package com.facebook.quicklog.identifiers;

public class FeedFullscreen {
    public static final int FEED_FULLSCREEN_TRANSITION = 61145089;
    public static final short MODULE_ID = 933;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FEED_FULLSCREEN_FEED_FULLSCREEN_TRANSITION";
    }
}
