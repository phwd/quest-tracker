package com.facebook.quicklog.identifiers;

public class Relay {
    public static final int FETCH_QUERY = 8009571;
    public static final int GROUPS_INIT_FETCH_TIME = 7995393;
    public static final short MODULE_ID = 122;
    public static final int PREFETCHER_FETCH_QUERY = 7995395;
    public static final int RELAY_PLAYGROUND_TTI = 7995394;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 14179 ? "UNDEFINED_QPL_EVENT" : "RELAY_FETCH_QUERY" : "RELAY_PREFETCHER_FETCH_QUERY" : "RELAY_RELAY_PLAYGROUND_TTI" : "RELAY_GROUPS_INIT_FETCH_TIME";
    }
}
