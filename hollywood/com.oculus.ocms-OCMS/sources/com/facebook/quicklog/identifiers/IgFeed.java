package com.facebook.quicklog.identifiers;

public class IgFeed {
    public static final int IG_FEED_LOAD = 19070977;
    public static final int IG_FEED_LOAD_MORE = 19070978;
    public static final short MODULE_ID = 291;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "IG_FEED_IG_FEED_LOAD_MORE" : "IG_FEED_IG_FEED_LOAD";
    }
}
