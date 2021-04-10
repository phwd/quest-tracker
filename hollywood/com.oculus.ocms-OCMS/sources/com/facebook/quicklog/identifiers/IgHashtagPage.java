package com.facebook.quicklog.identifiers;

public class IgHashtagPage {
    public static final int IG_HASHTAG_FEED_PTR_LOAD = 20643842;
    public static final int IG_HASHTAG_FEED_TAB_LOAD = 20643841;
    public static final int IG_HASHTAG_FEED_TAIL_LOAD = 20643843;
    public static final int IG_HASHTAG_HEADER_LOAD = 20643846;
    public static final short MODULE_ID = 315;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 6 ? "UNDEFINED_QPL_EVENT" : "IG_HASHTAG_PAGE_IG_HASHTAG_HEADER_LOAD" : "IG_HASHTAG_PAGE_IG_HASHTAG_FEED_TAIL_LOAD" : "IG_HASHTAG_PAGE_IG_HASHTAG_FEED_PTR_LOAD" : "IG_HASHTAG_PAGE_IG_HASHTAG_FEED_TAB_LOAD";
    }
}
