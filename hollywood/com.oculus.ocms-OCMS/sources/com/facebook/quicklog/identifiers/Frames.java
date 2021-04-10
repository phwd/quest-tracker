package com.facebook.quicklog.identifiers;

public class Frames {
    public static final int FRAME_ASSET_DOWNLOAD = 7471106;
    public static final short MODULE_ID = 114;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "FRAMES_FRAME_ASSET_DOWNLOAD";
    }
}
