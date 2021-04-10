package com.facebook.quicklog.identifiers;

public class Acela {
    public static final int ACELA_MAP_PINS_FETCH = 57606146;
    public static final int ACELA_MODULE_LOADING = 57606145;
    public static final short MODULE_ID = 879;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "ACELA_ACELA_MAP_PINS_FETCH" : "ACELA_ACELA_MODULE_LOADING";
    }
}
