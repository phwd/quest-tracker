package com.facebook.quicklog.identifiers;

public class GemstoneInterested {
    public static final short MODULE_ID = 476;
    public static final int NULL_STATE_RENDER = 31195138;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "GEMSTONE_INTERESTED_NULL_STATE_RENDER";
    }
}
