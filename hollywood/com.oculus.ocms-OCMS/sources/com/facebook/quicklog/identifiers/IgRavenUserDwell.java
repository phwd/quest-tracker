package com.facebook.quicklog.identifiers;

public class IgRavenUserDwell {
    public static final int DWELL = 51249153;
    public static final short MODULE_ID = 782;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_RAVEN_USER_DWELL_DWELL";
    }
}
