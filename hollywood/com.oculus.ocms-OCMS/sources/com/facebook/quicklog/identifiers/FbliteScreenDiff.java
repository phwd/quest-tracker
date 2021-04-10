package com.facebook.quicklog.identifiers;

public class FbliteScreenDiff {
    public static final short MODULE_ID = 674;
    public static final int SCREEN_DIFF = 44171265;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FBLITE_SCREEN_DIFF_SCREEN_DIFF";
    }
}
