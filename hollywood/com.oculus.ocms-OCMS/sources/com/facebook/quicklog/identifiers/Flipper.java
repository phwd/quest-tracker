package com.facebook.quicklog.identifiers;

public class Flipper {
    public static final short MODULE_ID = 794;
    public static final int STARTUP = 52035585;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FLIPPER_STARTUP";
    }
}
