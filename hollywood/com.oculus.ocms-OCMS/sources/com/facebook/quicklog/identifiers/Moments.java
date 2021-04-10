package com.facebook.quicklog.identifiers;

public class Moments {
    public static final int COLD_START = 10747905;
    public static final short MODULE_ID = 164;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MOMENTS_COLD_START";
    }
}
