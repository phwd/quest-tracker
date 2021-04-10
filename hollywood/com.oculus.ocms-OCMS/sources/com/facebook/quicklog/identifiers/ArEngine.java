package com.facebook.quicklog.identifiers;

public class ArEngine {
    public static final int FRAME_VIEWPORT_RENDER_TIME = 15925249;
    public static final short MODULE_ID = 243;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "AR_ENGINE_FRAME_VIEWPORT_RENDER_TIME";
    }
}
