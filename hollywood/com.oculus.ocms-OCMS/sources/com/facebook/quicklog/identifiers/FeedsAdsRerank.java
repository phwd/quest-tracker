package com.facebook.quicklog.identifiers;

public class FeedsAdsRerank {
    public static final short MODULE_ID = 415;
    public static final int ORGANIC_STORY_RERANK = 27197441;
    public static final int SPONSORED_STORY_RERANK = 27197442;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "FEEDS_ADS_RERANK_SPONSORED_STORY_RERANK" : "FEEDS_ADS_RERANK_ORGANIC_STORY_RERANK";
    }
}
