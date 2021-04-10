package com.facebook.quicklog.identifiers;

public class PresenceNow {
    public static final short MODULE_ID = 85;
    public static final int NOW_PERF_STATUS_LIST_LOADED_FRESH = 5570561;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "PRESENCE_NOW_NOW_PERF_STATUS_LIST_LOADED_FRESH";
    }
}
