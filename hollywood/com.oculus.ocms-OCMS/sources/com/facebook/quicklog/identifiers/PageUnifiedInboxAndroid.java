package com.facebook.quicklog.identifiers;

public class PageUnifiedInboxAndroid {
    public static final int INSTAGRAM_DIRECT_THREAD_LIST_FULL_FETCH_GRAPHQL = 35127297;
    public static final int INSTAGRAM_DIRECT_THREAD_VIEW_FULL_FETCH_GRAPHQL = 35127298;
    public static final short MODULE_ID = 536;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "PAGE_UNIFIED_INBOX_ANDROID_INSTAGRAM_DIRECT_THREAD_VIEW_FULL_FETCH_GRAPHQL" : "PAGE_UNIFIED_INBOX_ANDROID_INSTAGRAM_DIRECT_THREAD_LIST_FULL_FETCH_GRAPHQL";
    }
}
