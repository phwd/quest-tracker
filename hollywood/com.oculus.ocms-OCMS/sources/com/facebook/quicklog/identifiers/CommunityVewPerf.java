package com.facebook.quicklog.identifiers;

public class CommunityVewPerf {
    public static final int COMMUNITY_VIEW_TTRC = 59047937;
    public static final short MODULE_ID = 901;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "COMMUNITY_VEW_PERF_COMMUNITY_VIEW_TTRC";
    }
}
