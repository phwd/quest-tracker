package com.facebook.quicklog.identifiers;

public class IgProfile {
    public static final int DISPLAY_SIMILAR_ACCOUNTS = 23330817;
    public static final short MODULE_ID = 356;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_PROFILE_DISPLAY_SIMILAR_ACCOUNTS";
    }
}
