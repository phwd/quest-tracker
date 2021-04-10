package com.facebook.quicklog.identifiers;

public class Startup {
    public static final int ACTIVITY_APP_ENTRY = 8388625;
    public static final short MODULE_ID = 128;

    public static String getMarkerName(int i) {
        return i != 17 ? "UNDEFINED_QPL_EVENT" : "STARTUP_ACTIVITY_APP_ENTRY";
    }
}
