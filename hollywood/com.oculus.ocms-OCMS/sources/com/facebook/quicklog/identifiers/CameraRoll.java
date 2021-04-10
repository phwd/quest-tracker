package com.facebook.quicklog.identifiers;

public class CameraRoll {
    public static final int CAMERA_ROLL_RELIABILITY_ANDROID = 34537473;
    public static final short MODULE_ID = 527;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CAMERA_ROLL_CAMERA_ROLL_RELIABILITY_ANDROID";
    }
}
