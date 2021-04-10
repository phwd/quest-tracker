package com.facebook.quicklog.identifiers;

public class LiveFeed {
    public static final int CONNECT = 41680898;
    public static final int DISCONNECT = 41680899;
    public static final int IMPORTANT_FEED_STORY_RECEIVE = 41680900;
    public static final short MODULE_ID = 636;
    public static final int REALTIME_PRIVACY_INVALIDATION = 41706867;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? i != 25971 ? "UNDEFINED_QPL_EVENT" : "LIVE_FEED_REALTIME_PRIVACY_INVALIDATION" : "LIVE_FEED_IMPORTANT_FEED_STORY_RECEIVE" : "LIVE_FEED_DISCONNECT" : "LIVE_FEED_CONNECT";
    }
}
