package com.facebook.quicklog.identifiers;

public class Preload {
    public static final int FB_PRELOADER = 35454978;
    public static final short MODULE_ID = 541;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "PRELOAD_FB_PRELOADER";
    }
}
