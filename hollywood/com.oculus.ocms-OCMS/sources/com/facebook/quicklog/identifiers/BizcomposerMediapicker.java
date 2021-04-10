package com.facebook.quicklog.identifiers;

public class BizcomposerMediapicker {
    public static final int MEDIAPICKER_LAUNCH_TIME = 58327041;
    public static final short MODULE_ID = 890;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "BIZCOMPOSER_MEDIAPICKER_MEDIAPICKER_LAUNCH_TIME";
    }
}
