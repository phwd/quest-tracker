package com.facebook.quicklog.identifiers;

public class Watch {
    public static final short MODULE_ID = 272;
    public static final int WARION_PREFETCH_EVENT = 17839432;
    public static final int WATCH_DATA_FRESHNESS_EVENT = 17825798;
    public static final int WATCH_SPINNER_VPVD = 17825797;
    public static final int WATCH_WATCHLIST_LOAD = 17825793;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 13640 ? i != 5 ? i != 6 ? "UNDEFINED_QPL_EVENT" : "WATCH_WATCH_DATA_FRESHNESS_EVENT" : "WATCH_WATCH_SPINNER_VPVD" : "WATCH_WARION_PREFETCH_EVENT" : "WATCH_WATCH_WATCHLIST_LOAD";
    }
}
