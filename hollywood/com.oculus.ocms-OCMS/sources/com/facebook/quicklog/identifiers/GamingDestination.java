package com.facebook.quicklog.identifiers;

public class GamingDestination {
    public static final int CALCULATE_LAYOUT_STATE = 22151181;
    public static final int GAMES_APP_TTRC = 22151185;
    public static final int GAMING_FBGG_DEEPLINK_TTI = 22151184;
    public static final int GAMING_SURFACE_TRANSITION_TTRC = 22151186;
    public static final int GAMING_TAB_PAGINATION_TTI = 22151178;
    public static final int GAMING_TAB_PAGING_TTI = 22151176;
    public static final int GAMING_TAB_TTRC = 22151171;
    public static final int INIT_RANGE = 22151182;
    public static final int LAYOUT_STATE_FUTURE_GET_WAIT = 22151183;
    public static final int MOBILE_FEED_OPTIMIZED_GV_PAGE_LOAD_TIME = 22151173;
    public static final int MOBILE_FEED_OPTIMIZED_IG_PAGE_LOAD_TIME = 22151172;
    public static final int MOBILE_FEED_PAGE_LOAD_TIME = 22151170;
    public static final short MODULE_ID = 338;
    public static final int MOUNT = 22151180;
    public static final int VERSE_THREADVIEW_SHOW = 22151179;

    public static String getMarkerName(int i) {
        switch (i) {
            case 2:
                return "GAMING_DESTINATION_MOBILE_FEED_PAGE_LOAD_TIME";
            case 3:
                return "GAMING_DESTINATION_GAMING_TAB_TTRC";
            case 4:
                return "GAMING_DESTINATION_MOBILE_FEED_OPTIMIZED_IG_PAGE_LOAD_TIME";
            case 5:
                return "GAMING_DESTINATION_MOBILE_FEED_OPTIMIZED_GV_PAGE_LOAD_TIME";
            case 6:
            case 7:
            case 9:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 8:
                return "GAMING_DESTINATION_GAMING_TAB_PAGING_TTI";
            case 10:
                return "GAMING_DESTINATION_GAMING_TAB_PAGINATION_TTI";
            case 11:
                return "GAMING_DESTINATION_VERSE_THREADVIEW_SHOW";
            case 12:
                return "GAMING_DESTINATION_MOUNT";
            case 13:
                return "GAMING_DESTINATION_CALCULATE_LAYOUT_STATE";
            case 14:
                return "GAMING_DESTINATION_INIT_RANGE";
            case 15:
                return "GAMING_DESTINATION_LAYOUT_STATE_FUTURE_GET_WAIT";
            case 16:
                return "GAMING_DESTINATION_GAMING_FBGG_DEEPLINK_TTI";
            case 17:
                return "GAMING_DESTINATION_GAMES_APP_TTRC";
            case 18:
                return "GAMING_DESTINATION_GAMING_SURFACE_TRANSITION_TTRC";
        }
    }
}
