package com.facebook.quicklog.identifiers;

public class FbcVenice {
    public static final short MODULE_ID = 14020;
    public static final int UI_USAGE = 918824910;

    public static String getMarkerName(int i) {
        return i != 10190 ? "UNDEFINED_QPL_EVENT" : "FBC_VENICE_UI_USAGE";
    }
}
