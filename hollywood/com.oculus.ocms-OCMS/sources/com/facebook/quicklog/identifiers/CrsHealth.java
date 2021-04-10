package com.facebook.quicklog.identifiers;

public class CrsHealth {
    public static final int FEED_DEDUPLICATION_ANDROID = 40960002;
    public static final int FEED_NIL_NODE_FILTER_IOS = 40960003;
    public static final int FEED_REQUESTS = 40960004;
    public static final short MODULE_ID = 625;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "CRS_HEALTH_FEED_REQUESTS" : "CRS_HEALTH_FEED_NIL_NODE_FILTER_IOS" : "CRS_HEALTH_FEED_DEDUPLICATION_ANDROID";
    }
}
