package com.facebook.quicklog.identifiers;

public class FbShorts {
    public static final int BLUEREELS_VIEWER_INITIAL_LOAD = 594092237;
    public static final int CONTEXTUAL_PROFILE_TTRC = 594091641;
    public static final int IN_FEED_UNIT_CLIENT_POOL = 594095382;
    public static final short MODULE_ID = 9065;
    public static final int POSITION_0_SCROLL = 594091025;
    public static final int PROFILE_ACTION = 594085884;
    public static final int USER_INTERACTION = 594090570;
    public static final int VIEWER_SCROLL_LOAD = 594094608;

    public static String getMarkerName(int i) {
        return i != 2044 ? i != 6730 ? i != 7185 ? i != 7801 ? i != 8397 ? i != 10768 ? i != 11542 ? "UNDEFINED_QPL_EVENT" : "FB_SHORTS_IN_FEED_UNIT_CLIENT_POOL" : "FB_SHORTS_VIEWER_SCROLL_LOAD" : "FB_SHORTS_BLUEREELS_VIEWER_INITIAL_LOAD" : "FB_SHORTS_CONTEXTUAL_PROFILE_TTRC" : "FB_SHORTS_POSITION_0_SCROLL" : "FB_SHORTS_USER_INTERACTION" : "FB_SHORTS_PROFILE_ACTION";
    }
}
