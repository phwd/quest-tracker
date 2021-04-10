package com.facebook.quicklog.identifiers;

public class Dei {
    public static final int INTERACTION = 48693249;
    public static final short MODULE_ID = 743;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "DEI_INTERACTION";
    }
}
