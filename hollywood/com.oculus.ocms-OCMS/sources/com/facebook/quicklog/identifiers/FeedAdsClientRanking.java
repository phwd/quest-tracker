package com.facebook.quicklog.identifiers;

public class FeedAdsClientRanking {
    public static final int ASYNC_PREDICTION = 63111169;
    public static final short MODULE_ID = 963;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FEED_ADS_CLIENT_RANKING_ASYNC_PREDICTION";
    }
}
