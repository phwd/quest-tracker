package com.facebook.quicklog.identifiers;

public class VideoHome {
    public static final int ARLTW_ENTRY_VIDEO_PLAY_START_ANDROID = 10223632;
    public static final int ARLTW_ENTRY_VIDEO_PLAY_START_IOS = 10223628;
    public static final short MODULE_ID = 156;
    public static final int MUSIC_HOME_TTI = 10223635;
    public static final int NOTIF_HUB_UPDATES_SURFACE_TTRC = 10223636;
    public static final int VIDEO_HOME_VIDEOS_TAB_TTI = 10223626;
    public static final int WATCH_ARLTW_TRANSITION = 10223633;
    public static final int WATCH_DATA_FETCH = 10223634;
    public static final int WATCH_DEEPLINK_STORY_FETCH = 10223638;
    public static final int WATCH_ENTRY_POINT_STORY_FETCH = 10223639;
    public static final int WATCH_TAB_PAGINATION_TTI = 10223620;
    public static final int WATCH_TAB_TAIL_LOAD_TTI = 10223637;
    public static final int WATCH_TOPIC_FEED_TTI = 10223627;
    public static final int WATCH_TOPIC_FEED_TTRC = 10223630;
    public static final int WATCH_WATCHLIST_AGGREGATION_TTI = 10223623;

    public static String getMarkerName(int i) {
        switch (i) {
            case 4:
                return "VIDEO_HOME_WATCH_TAB_PAGINATION_TTI";
            case 5:
            case 6:
            case 8:
            case 9:
            case 13:
            case 15:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 7:
                return "VIDEO_HOME_WATCH_WATCHLIST_AGGREGATION_TTI";
            case 10:
                return "VIDEO_HOME_VIDEO_HOME_VIDEOS_TAB_TTI";
            case 11:
                return "VIDEO_HOME_WATCH_TOPIC_FEED_TTI";
            case 12:
                return "VIDEO_HOME_ARLTW_ENTRY_VIDEO_PLAY_START_IOS";
            case 14:
                return "VIDEO_HOME_WATCH_TOPIC_FEED_TTRC";
            case 16:
                return "VIDEO_HOME_ARLTW_ENTRY_VIDEO_PLAY_START_ANDROID";
            case 17:
                return "VIDEO_HOME_WATCH_ARLTW_TRANSITION";
            case 18:
                return "VIDEO_HOME_WATCH_DATA_FETCH";
            case 19:
                return "VIDEO_HOME_MUSIC_HOME_TTI";
            case 20:
                return "VIDEO_HOME_NOTIF_HUB_UPDATES_SURFACE_TTRC";
            case 21:
                return "VIDEO_HOME_WATCH_TAB_TAIL_LOAD_TTI";
            case 22:
                return "VIDEO_HOME_WATCH_DEEPLINK_STORY_FETCH";
            case 23:
                return "VIDEO_HOME_WATCH_ENTRY_POINT_STORY_FETCH";
        }
    }
}
