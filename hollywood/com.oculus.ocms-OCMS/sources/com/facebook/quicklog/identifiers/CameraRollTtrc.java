package com.facebook.quicklog.identifiers;

public class CameraRollTtrc {
    public static final int CAMERA_ROLL_TTRC_ANDROID = 34930689;
    public static final short MODULE_ID = 533;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CAMERA_ROLL_TTRC_CAMERA_ROLL_TTRC_ANDROID";
    }
}
