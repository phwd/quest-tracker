package com.facebook.quicklog.identifiers;

public class LassoAndroidVideoPlayback {
    public static final short MODULE_ID = 540;
    public static final int VIDEO_SIMULTANEOUS_AUTOPLAY_RUNNABLE = 35389441;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "LASSO_ANDROID_VIDEO_PLAYBACK_VIDEO_SIMULTANEOUS_AUTOPLAY_RUNNABLE";
    }
}
