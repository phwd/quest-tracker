package com.facebook.quicklog.identifiers;

public class HighSchoolNetworks {
    public static final int INVITE_LOAD = 33161217;
    public static final short MODULE_ID = 506;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "HIGH_SCHOOL_NETWORKS_INVITE_LOAD";
    }
}
