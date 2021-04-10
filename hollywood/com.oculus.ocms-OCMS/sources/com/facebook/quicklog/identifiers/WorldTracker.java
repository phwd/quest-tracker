package com.facebook.quicklog.identifiers;

public class WorldTracker {
    public static final short MODULE_ID = 247;
    public static final int WORLD_TRACKER_START_TRACKING = 16187395;
    public static final int WORLD_TRACKER_UPDATE_FRAME = 16187394;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "WORLD_TRACKER_WORLD_TRACKER_START_TRACKING" : "WORLD_TRACKER_WORLD_TRACKER_UPDATE_FRAME";
    }
}
