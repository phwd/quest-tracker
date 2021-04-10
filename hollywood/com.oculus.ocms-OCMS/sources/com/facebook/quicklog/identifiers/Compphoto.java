package com.facebook.quicklog.identifiers;

public class Compphoto {
    public static final int CAMERA_FRAME_RENDER_TIME_MS_AVG = 60030978;
    public static final short MODULE_ID = 916;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "COMPPHOTO_CAMERA_FRAME_RENDER_TIME_MS_AVG";
    }
}
