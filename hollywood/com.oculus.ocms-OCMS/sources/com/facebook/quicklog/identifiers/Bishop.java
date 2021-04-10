package com.facebook.quicklog.identifiers;

public class Bishop {
    public static final short MODULE_ID = 710;
    public static final int START_UP = 46540184;

    public static String getMarkerName(int i) {
        return i != 9624 ? "UNDEFINED_QPL_EVENT" : "BISHOP_START_UP";
    }
}
