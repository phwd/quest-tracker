package com.facebook.quicklog.identifiers;

public class IgAndroidVideoRender {
    public static final short MODULE_ID = 774;
    public static final int VIDEO_RENDER = 50724865;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_ANDROID_VIDEO_RENDER_VIDEO_RENDER";
    }
}
