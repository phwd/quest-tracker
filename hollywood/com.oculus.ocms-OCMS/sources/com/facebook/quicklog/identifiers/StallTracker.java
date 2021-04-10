package com.facebook.quicklog.identifiers;

public class StallTracker {
    public static final short MODULE_ID = 268;
    public static final int STALL_TRACKER_SESSION = 17563649;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "STALL_TRACKER_STALL_TRACKER_SESSION";
    }
}
