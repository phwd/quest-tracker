package com.facebook.quicklog.identifiers;

public class CommunityPopularNow {
    public static final short MODULE_ID = 8316;
    public static final int POPULAR_SURFACE_TTRC_ANDROID = 545000599;

    public static String getMarkerName(int i) {
        return i != 3223 ? "UNDEFINED_QPL_EVENT" : "COMMUNITY_POPULAR_NOW_POPULAR_SURFACE_TTRC_ANDROID";
    }
}
