package com.facebook.quicklog.identifiers;

public class PrCameraLite {
    public static final short MODULE_ID = 461;
    public static final int PR_CAMERA_LITE_BURN = 30212098;
    public static final int PR_CAMERA_LITE_UEG_OPEN = 30212097;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "PR_CAMERA_LITE_PR_CAMERA_LITE_BURN" : "PR_CAMERA_LITE_PR_CAMERA_LITE_UEG_OPEN";
    }
}
