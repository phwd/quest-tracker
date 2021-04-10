package com.facebook.quicklog.identifiers;

public class PymkFeedUnit {
    public static final int CALCULATE_LAYOUT_STATE = 47054850;
    public static final int INIT_RANGE = 47054851;
    public static final int LAYOUT_STATE_FUTURE_GET_WAIT = 47054852;
    public static final short MODULE_ID = 718;
    public static final int MOUNT = 47054849;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "PYMK_FEED_UNIT_LAYOUT_STATE_FUTURE_GET_WAIT" : "PYMK_FEED_UNIT_INIT_RANGE" : "PYMK_FEED_UNIT_CALCULATE_LAYOUT_STATE" : "PYMK_FEED_UNIT_MOUNT";
    }
}
