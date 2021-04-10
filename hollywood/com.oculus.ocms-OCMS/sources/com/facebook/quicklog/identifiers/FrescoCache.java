package com.facebook.quicklog.identifiers;

public class FrescoCache {
    public static final int FRESCO_CACHE_OBSERVER = 58982401;
    public static final short MODULE_ID = 900;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FRESCO_CACHE_FRESCO_CACHE_OBSERVER";
    }
}
