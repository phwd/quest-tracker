package com.facebook.quicklog.identifiers;

public class Typeface {
    public static final int FORCED_TYPEFACE_OVERRIDE = 2818049;
    public static final short MODULE_ID = 43;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "TYPEFACE_FORCED_TYPEFACE_OVERRIDE";
    }
}
