package com.facebook.quicklog.identifiers;

public class GroupsFeed {
    public static final int FEED_TTI = 1114113;
    public static final short MODULE_ID = 17;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "GROUPS_FEED_FEED_TTI";
    }
}
