package com.facebook.quicklog.identifiers;

public class Zopt {
    public static final short MODULE_ID = 764;
    public static final int OPTIMIZE = 50069505;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ZOPT_OPTIMIZE";
    }
}
