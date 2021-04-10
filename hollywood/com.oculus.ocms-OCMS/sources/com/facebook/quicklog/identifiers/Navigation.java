package com.facebook.quicklog.identifiers;

public class Navigation {
    public static final int BADGE_FETCH = 25952261;
    public static final int FB4A_NAVIGATION_SETUP = 25952265;
    public static final short MODULE_ID = 396;
    public static final int PRELOAD_MANAGER = 25952267;
    public static final int SESSION = 25952257;
    public static final int TAB_CLICK_POST_CONTENT_INIT_SETUP = 25952263;
    public static final int TAB_CLICK_PRE_CONTENT_INIT_SETUP = 25952262;
    public static final int TAB_SETUP = 25952264;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "NAVIGATION_SESSION";
        }
        if (i == 11) {
            return "NAVIGATION_PRELOAD_MANAGER";
        }
        switch (i) {
            case 5:
                return "NAVIGATION_BADGE_FETCH";
            case 6:
                return "NAVIGATION_TAB_CLICK_PRE_CONTENT_INIT_SETUP";
            case 7:
                return "NAVIGATION_TAB_CLICK_POST_CONTENT_INIT_SETUP";
            case 8:
                return "NAVIGATION_TAB_SETUP";
            case 9:
                return "NAVIGATION_FB4A_NAVIGATION_SETUP";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
