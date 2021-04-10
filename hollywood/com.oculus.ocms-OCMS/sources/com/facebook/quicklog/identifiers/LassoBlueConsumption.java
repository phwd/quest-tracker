package com.facebook.quicklog.identifiers;

public class LassoBlueConsumption {
    public static final int IN_FEED_UNIT_CLIENT_POOL = 56623107;
    public static final short MODULE_ID = 864;
    public static final int POSITION_0_SCROLL = 56623105;
    public static final int USER_INTERACTION = 56623106;
    public static final int VIEWER_SCROLL_LOAD = 56629329;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 6225 ? "UNDEFINED_QPL_EVENT" : "LASSO_BLUE_CONSUMPTION_VIEWER_SCROLL_LOAD" : "LASSO_BLUE_CONSUMPTION_IN_FEED_UNIT_CLIENT_POOL" : "LASSO_BLUE_CONSUMPTION_USER_INTERACTION" : "LASSO_BLUE_CONSUMPTION_POSITION_0_SCROLL";
    }
}
