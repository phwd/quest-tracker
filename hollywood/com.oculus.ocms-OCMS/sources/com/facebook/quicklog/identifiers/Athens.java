package com.facebook.quicklog.identifiers;

public class Athens {
    public static final short MODULE_ID = 454;
    public static final int SURFACE_TTRC = 29753345;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ATHENS_SURFACE_TTRC";
    }
}
