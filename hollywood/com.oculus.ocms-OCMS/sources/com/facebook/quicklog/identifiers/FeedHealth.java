package com.facebook.quicklog.identifiers;

public class FeedHealth {
    public static final int LIKE_REACT_FAILURE = 45023236;
    public static final int LOAD_COMMENTS_FAILURE = 45023234;
    public static final int LOAD_PHOTOS_FEED_FAILURE = 45023235;
    public static final short MODULE_ID = 687;
    public static final int SEND_COMMENT_FAILURE = 45023233;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "FEED_HEALTH_LIKE_REACT_FAILURE" : "FEED_HEALTH_LOAD_PHOTOS_FEED_FAILURE" : "FEED_HEALTH_LOAD_COMMENTS_FAILURE" : "FEED_HEALTH_SEND_COMMENT_FAILURE";
    }
}
