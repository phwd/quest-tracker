package com.facebook.quicklog.identifiers;

public class CameraFlow {
    public static final int ACTIVITY_CREATE = 2424833;
    public static final short MODULE_ID = 37;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CAMERA_FLOW_ACTIVITY_CREATE";
    }
}
