package com.facebook.quicklog.identifiers;

public class Divebar {
    public static final int DIVEBAR_SURFACE_LOAD = 6225921;
    public static final int DIVEBAR_SURFACE_LOAD_FRESH = 6225922;
    public static final short MODULE_ID = 95;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "DIVEBAR_DIVEBAR_SURFACE_LOAD_FRESH" : "DIVEBAR_DIVEBAR_SURFACE_LOAD";
    }
}
