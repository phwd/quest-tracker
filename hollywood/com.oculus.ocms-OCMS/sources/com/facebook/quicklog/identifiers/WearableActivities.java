package com.facebook.quicklog.identifiers;

public class WearableActivities {
    public static final int APP_START = 435755337;
    public static final short MODULE_ID = 6649;

    public static String getMarkerName(int i) {
        return i != 6473 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_ACTIVITIES_APP_START";
    }
}
