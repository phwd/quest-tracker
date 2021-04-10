package com.facebook.quicklog.identifiers;

public class VideoAdsWatchAndBrowseFullscreen {
    public static final short MODULE_ID = 936;
    public static final int WATCH_AND_BROWSE_FULLSCREEN_TRANSITION = 61341697;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "VIDEO_ADS_WATCH_AND_BROWSE_FULLSCREEN_WATCH_AND_BROWSE_FULLSCREEN_TRANSITION";
    }
}
