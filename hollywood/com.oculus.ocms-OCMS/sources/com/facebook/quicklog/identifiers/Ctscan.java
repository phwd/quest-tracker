package com.facebook.quicklog.identifiers;

public class Ctscan {
    public static final int MOBILE_CONFIG_SYNCED = 12648449;
    public static final short MODULE_ID = 193;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CTSCAN_MOBILE_CONFIG_SYNCED";
    }
}
