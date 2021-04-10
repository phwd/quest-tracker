package com.facebook.quicklog.identifiers;

public class WearableDevoptions {
    public static final int COLD_START = 1054487111;
    public static final short MODULE_ID = 16090;

    public static String getMarkerName(int i) {
        return i != 12871 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_DEVOPTIONS_COLD_START";
    }
}
