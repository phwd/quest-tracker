package com.facebook.quicklog.identifiers;

public class Threadview {
    public static final short MODULE_ID = 460;
    public static final int ROW_GENERATION_THREADVIEW_ANDROID = 30146561;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "THREADVIEW_ROW_GENERATION_THREADVIEW_ANDROID";
    }
}
