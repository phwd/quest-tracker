package com.facebook.quicklog.identifiers;

public class IgVideo {
    public static final int ABR_DECISION = 18743298;
    public static final short MODULE_ID = 286;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "IG_VIDEO_ABR_DECISION";
    }
}
