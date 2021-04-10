package com.facebook.quicklog.identifiers;

public class IgReelsCamera {
    public static final short MODULE_ID = 12796;
    public static final int TIME_TO_LOAD_CLIPS_POST_CAPTURE = 838610345;

    public static String getMarkerName(int i) {
        return i != 11689 ? "UNDEFINED_QPL_EVENT" : "IG_REELS_CAMERA_TIME_TO_LOAD_CLIPS_POST_CAPTURE";
    }
}
