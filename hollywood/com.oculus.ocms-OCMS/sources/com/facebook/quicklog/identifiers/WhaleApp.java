package com.facebook.quicklog.identifiers;

public class WhaleApp {
    public static final int FEED_OPEN_FUNDRAISER_PAGE = 61877807;
    public static final short MODULE_ID = 944;

    public static String getMarkerName(int i) {
        return i != 11823 ? "UNDEFINED_QPL_EVENT" : "WHALE_APP_FEED_OPEN_FUNDRAISER_PAGE";
    }
}
