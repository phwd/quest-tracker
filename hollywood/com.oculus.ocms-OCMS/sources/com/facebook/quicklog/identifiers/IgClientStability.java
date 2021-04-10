package com.facebook.quicklog.identifiers;

public class IgClientStability {
    public static final int IMPRESSION_SCAN = 17694721;
    public static final short MODULE_ID = 270;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_CLIENT_STABILITY_IMPRESSION_SCAN";
    }
}
