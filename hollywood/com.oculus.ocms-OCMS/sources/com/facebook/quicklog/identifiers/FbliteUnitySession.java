package com.facebook.quicklog.identifiers;

public class FbliteUnitySession {
    public static final int FBLITE_BADGE_COUNTS = 43646978;
    public static final int FBLITE_UNITY_STARTUP = 43646977;
    public static final short MODULE_ID = 666;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "FBLITE_UNITY_SESSION_FBLITE_BADGE_COUNTS" : "FBLITE_UNITY_SESSION_FBLITE_UNITY_STARTUP";
    }
}
