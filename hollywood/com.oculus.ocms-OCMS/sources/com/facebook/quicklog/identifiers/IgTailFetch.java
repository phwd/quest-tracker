package com.facebook.quicklog.identifiers;

public class IgTailFetch {
    public static final int EXPLORE_POPULAR = 399507460;
    public static final int FEED_TIMELINE = 399507457;
    public static final int IG_PROFILE = 399507458;
    public static final short MODULE_ID = 6096;
    public static final int REELS_VIEWER = 399507459;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "IG_TAIL_FETCH_EXPLORE_POPULAR" : "IG_TAIL_FETCH_REELS_VIEWER" : "IG_TAIL_FETCH_IG_PROFILE" : "IG_TAIL_FETCH_FEED_TIMELINE";
    }
}
