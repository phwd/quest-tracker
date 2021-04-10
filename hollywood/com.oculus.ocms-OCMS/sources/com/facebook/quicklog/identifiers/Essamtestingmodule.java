package com.facebook.quicklog.identifiers;

public class Essamtestingmodule {
    public static final int ANOTHEREVENT = 52428806;
    public static final short MODULE_ID = 800;

    public static String getMarkerName(int i) {
        return i != 6 ? "UNDEFINED_QPL_EVENT" : "ESSAMTESTINGMODULE_ANOTHEREVENT";
    }
}
