package com.facebook.quicklog.identifiers;

public class ExpressiveReshares {
    public static final short MODULE_ID = 4584;
    public static final int MOOD_FILTER_CONSUMPTION = 300429672;

    public static String getMarkerName(int i) {
        return i != 12648 ? "UNDEFINED_QPL_EVENT" : "EXPRESSIVE_RESHARES_MOOD_FILTER_CONSUMPTION";
    }
}
