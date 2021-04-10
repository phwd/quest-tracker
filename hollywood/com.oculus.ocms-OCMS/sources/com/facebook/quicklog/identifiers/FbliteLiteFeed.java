package com.facebook.quicklog.identifiers;

public class FbliteLiteFeed {
    public static final int FBLITE_FEED_STARTUP_CLIENT = 35979272;
    public static final int FBLITE_HEAD_FETCH = 35979270;
    public static final int FBLITE_TAIL_LOAD = 35979271;
    public static final int FEED_TEST = 35979273;
    public static final int INITIAL_FEED_DMG_TRACKER_FBLITE = 35979274;
    public static final int LITE_FEED_STARTUP = 35979266;
    public static final int LITE_FEED_STARTUP_CLIENT = 35979269;
    public static final short MODULE_ID = 549;
    public static final int SCREEN_DMG_TRACKER_FBLITE = 35979275;
    public static final int STORY_POOLS_ACTIONS = 35987642;
    public static final int TAIL_LOAD = 35979267;

    public static String getMarkerName(int i) {
        if (i == 2) {
            return "FBLITE_LITE_FEED_LITE_FEED_STARTUP";
        }
        if (i == 3) {
            return "FBLITE_LITE_FEED_TAIL_LOAD";
        }
        if (i == 8378) {
            return "FBLITE_LITE_FEED_STORY_POOLS_ACTIONS";
        }
        switch (i) {
            case 5:
                return "FBLITE_LITE_FEED_LITE_FEED_STARTUP_CLIENT";
            case 6:
                return "FBLITE_LITE_FEED_FBLITE_HEAD_FETCH";
            case 7:
                return "FBLITE_LITE_FEED_FBLITE_TAIL_LOAD";
            case 8:
                return "FBLITE_LITE_FEED_FBLITE_FEED_STARTUP_CLIENT";
            case 9:
                return "FBLITE_LITE_FEED_FEED_TEST";
            case 10:
                return "FBLITE_LITE_FEED_INITIAL_FEED_DMG_TRACKER_FBLITE";
            case 11:
                return "FBLITE_LITE_FEED_SCREEN_DMG_TRACKER_FBLITE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
