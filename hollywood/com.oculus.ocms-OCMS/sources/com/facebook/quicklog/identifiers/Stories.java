package com.facebook.quicklog.identifiers;

public class Stories {
    public static final int BUCKET_NAVIGATION_TTI = 13238294;
    public static final int CARD_DELETION = 13238332;
    public static final int DISK_CACHE_INITIALIZE = 13238303;
    public static final int DISK_CACHE_WRITE = 13238304;
    public static final int FB_STORY_ADMIN_PAGE_SCROLL_PERF = 13238334;
    public static final int FB_STORY_VIEWER_LIST_SCROLL_PERF = 13238335;
    public static final int FRIEND_STORY_REFRESH_TTI = 13238282;
    public static final int LIGHTWEIGHT_MESSAGING_TTRC = 13238347;
    public static final int LWR_TAP_ANIMATION_TTI = 13238316;
    public static final short MODULE_ID = 202;
    public static final int MY_STORY_BUCKET_REFRESH_TTI = 13238281;
    public static final int OPEN_STORIES_REPLY_IN_BLUE_FROM_JEWEL_NOTIFICATION_TTRC = 13238348;
    public static final int OPEN_STORIES_REPLY_IN_BLUE_PRODUCER_INITIATED_TTRC = 13238369;
    public static final int OPTIMISTIC_STORE_INSERT_TIME = 13238301;
    public static final int OPTIMISTIC_STORE_PURGE_TIME = 13238302;
    public static final int REPLY_BAR_TTI = 13238307;
    public static final int ROW_COLD_START_TTI = 13238277;
    public static final int ROW_REFRESH_TTI = 13238279;
    public static final int STORIES_ERRORS = 13238323;
    public static final int STORIES_SURFACE_TTRC = 13238375;
    public static final int STORIES_TRAY_BUCKET_AND_CARDS_FILTER = 13238381;
    public static final int STORIES_TRAY_TTRC = 13238320;
    public static final int STORY_BUCKET_INFLATION = 13238309;
    public static final int STORY_DOWNLOAD_RETRY = 13238388;
    public static final int STORY_PRIVACY_SETTING_OPEN_TTI = 13238393;
    public static final int STORY_VIEWER_SHEET_INITIAL_LOAD_TTI = 13238345;
    public static final int STORY_VIEWER_SHEET_INITIAL_LOAD_TTRC = 13238346;
    public static final int STORY_VIEWER_SURFACE_RESPONSIVENESS_FB4A = 13238350;
    public static final int STORY_VIEWER_SURFACE_RESPONSIVENESS_FB4A_30S_EVENT_BASED = 13238383;
    public static final int STORY_VIEWER_SURFACE_RESPONSIVENESS_FB4A_30S_USER_BASED = 13238382;
    public static final int SURFACE_COMPLETE_TTI = 13238376;
    public static final int SURFACE_LOAD_PAGINATION_TTI = 13238377;
    public static final int TEST_FREEMODE_META_DATA_PROVIDER = 13241132;
    public static final int THREAD_NAVIGATION_TTI = 13238295;
    public static final int TRAY_COMPLETE_TTI = 13238299;
    public static final int TRAY_FREE_MODE_META_DATA_PROVIDER_TEST = 13246486;
    public static final int TRAY_LIFECYCLE_TTI = 13238351;
    public static final int TRAY_LOAD_DEBUG = 13238368;
    public static final int TRAY_LOAD_PAGINATION_TTI = 13238297;
    public static final int TRAY_LOAD_TTI = 13238293;
    public static final int TRAY_NOT_LOADING = 13240890;
    public static final int TRAY_NOT_LOADING_SERVER = 13240895;
    public static final int VIEWER_FIRST_STORY_LOAD_TTI = 13238273;
    public static final int VIEWER_NEXT_BUCKET_TTI = 13238275;
    public static final int VIEWER_NEXT_STORY_TTI = 13238274;
    public static final int VIEWER_SHEET_DATA_FETCHING_RELIABILITY = 13255637;
    public static final int VIEWER_SHEET_ENTRY_POINT_CONSISTENCY = 13238365;
    public static final int VIEWER_SHEET_TTRC = 13238306;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "STORIES_VIEWER_FIRST_STORY_LOAD_TTI";
            case 2:
                return "STORIES_VIEWER_NEXT_STORY_TTI";
            case 3:
                return "STORIES_VIEWER_NEXT_BUCKET_TTI";
            case 5:
                return "STORIES_ROW_COLD_START_TTI";
            case 7:
                return "STORIES_ROW_REFRESH_TTI";
            case 9:
                return "STORIES_MY_STORY_BUCKET_REFRESH_TTI";
            case 10:
                return "STORIES_FRIEND_STORY_REFRESH_TTI";
            case 21:
                return "STORIES_TRAY_LOAD_TTI";
            case 22:
                return "STORIES_BUCKET_NAVIGATION_TTI";
            case 23:
                return "STORIES_THREAD_NAVIGATION_TTI";
            case 25:
                return "STORIES_TRAY_LOAD_PAGINATION_TTI";
            case 27:
                return "STORIES_TRAY_COMPLETE_TTI";
            case 29:
                return "STORIES_OPTIMISTIC_STORE_INSERT_TIME";
            case 30:
                return "STORIES_OPTIMISTIC_STORE_PURGE_TIME";
            case 31:
                return "STORIES_DISK_CACHE_INITIALIZE";
            case 32:
                return "STORIES_DISK_CACHE_WRITE";
            case 34:
                return "STORIES_VIEWER_SHEET_TTRC";
            case 35:
                return "STORIES_REPLY_BAR_TTI";
            case 37:
                return "STORIES_STORY_BUCKET_INFLATION";
            case 44:
                return "STORIES_LWR_TAP_ANIMATION_TTI";
            case 48:
                return "STORIES_STORIES_TRAY_TTRC";
            case 51:
                return "STORIES_STORIES_ERRORS";
            case 60:
                return "STORIES_CARD_DELETION";
            case 62:
                return "STORIES_FB_STORY_ADMIN_PAGE_SCROLL_PERF";
            case 63:
                return "STORIES_FB_STORY_VIEWER_LIST_SCROLL_PERF";
            case 73:
                return "STORIES_STORY_VIEWER_SHEET_INITIAL_LOAD_TTI";
            case 74:
                return "STORIES_STORY_VIEWER_SHEET_INITIAL_LOAD_TTRC";
            case 75:
                return "STORIES_LIGHTWEIGHT_MESSAGING_TTRC";
            case 76:
                return "STORIES_OPEN_STORIES_REPLY_IN_BLUE_FROM_JEWEL_NOTIFICATION_TTRC";
            case 78:
                return "STORIES_STORY_VIEWER_SURFACE_RESPONSIVENESS_FB4A";
            case 79:
                return "STORIES_TRAY_LIFECYCLE_TTI";
            case 93:
                return "STORIES_VIEWER_SHEET_ENTRY_POINT_CONSISTENCY";
            case 96:
                return "STORIES_TRAY_LOAD_DEBUG";
            case 97:
                return "STORIES_OPEN_STORIES_REPLY_IN_BLUE_PRODUCER_INITIATED_TTRC";
            case 103:
                return "STORIES_STORIES_SURFACE_TTRC";
            case 104:
                return "STORIES_SURFACE_COMPLETE_TTI";
            case 105:
                return "STORIES_SURFACE_LOAD_PAGINATION_TTI";
            case 109:
                return "STORIES_STORIES_TRAY_BUCKET_AND_CARDS_FILTER";
            case 110:
                return "STORIES_STORY_VIEWER_SURFACE_RESPONSIVENESS_FB4A_30S_USER_BASED";
            case 111:
                return "STORIES_STORY_VIEWER_SURFACE_RESPONSIVENESS_FB4A_30S_EVENT_BASED";
            case 116:
                return "STORIES_STORY_DOWNLOAD_RETRY";
            case 121:
                return "STORIES_STORY_PRIVACY_SETTING_OPEN_TTI";
            case 2618:
                return "STORIES_TRAY_NOT_LOADING";
            case 2623:
                return "STORIES_TRAY_NOT_LOADING_SERVER";
            case 2860:
                return "STORIES_TEST_FREEMODE_META_DATA_PROVIDER";
            case 8214:
                return "STORIES_TRAY_FREE_MODE_META_DATA_PROVIDER_TEST";
            case 17365:
                return "STORIES_VIEWER_SHEET_DATA_FETCHING_RELIABILITY";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
