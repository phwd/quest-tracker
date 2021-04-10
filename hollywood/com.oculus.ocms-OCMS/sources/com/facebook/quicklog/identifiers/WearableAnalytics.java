package com.facebook.quicklog.identifiers;

public class WearableAnalytics {
    public static final int EVENT_PERSIST_PIPELINE = 230176262;
    public static final short MODULE_ID = 3512;

    public static String getMarkerName(int i) {
        return i != 13830 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_ANALYTICS_EVENT_PERSIST_PIPELINE";
    }
}
