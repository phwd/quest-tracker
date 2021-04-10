package com.facebook.quicklog.identifiers;

public class BizBadge {
    public static final int CLIENT_FUNNEL_EVENT = 83365681;
    public static final short MODULE_ID = 1272;

    public static String getMarkerName(int i) {
        return i != 3889 ? "UNDEFINED_QPL_EVENT" : "BIZ_BADGE_CLIENT_FUNNEL_EVENT";
    }
}
