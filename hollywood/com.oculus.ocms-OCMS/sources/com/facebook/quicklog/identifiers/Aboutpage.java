package com.facebook.quicklog.identifiers;

public class Aboutpage {
    public static final int COLLECTION_LOAD = 12517377;
    public static final int COLLECTION_PTR = 12517382;
    public static final int COLLECTION_REFRESH = 12517378;
    public static final int COLLECTION_REFRESH_ON_RECONNECT = 12517381;
    public static final int COLLECTION_SCROLL_FETCH = 12517379;
    public static final short MODULE_ID = 191;
    public static final int TEST1 = 12536878;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 5 ? i != 6 ? i != 19502 ? "UNDEFINED_QPL_EVENT" : "ABOUTPAGE_TEST1" : "ABOUTPAGE_COLLECTION_PTR" : "ABOUTPAGE_COLLECTION_REFRESH_ON_RECONNECT" : "ABOUTPAGE_COLLECTION_SCROLL_FETCH" : "ABOUTPAGE_COLLECTION_REFRESH" : "ABOUTPAGE_COLLECTION_LOAD";
    }
}
