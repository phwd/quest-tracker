package com.facebook.quicklog.identifiers;

public class AppTabPerf {
    public static final int FB_BOOKMARKS = 6488070;
    public static final int FB_FEED = 6488065;
    public static final int FB_FRIEND_REQUESTS_TAB = 6488067;
    public static final int FB_MESSAGES_TAB = 6488068;
    public static final int FB_NOTIFICATIONS_TAB = 6488069;
    public static final int LOAD_TAB_BOOKMARK = 6488077;
    public static final int LOAD_TAB_BOOKMARK_NOANIM = 6488076;
    public static final int LOAD_TAB_MESSAGE = 6488071;
    public static final int LOAD_TAB_MESSAGE_NOANIM = 6488072;
    public static final int LOAD_TAB_NOTIFICATIONS = 6488074;
    public static final int LOAD_TAB_NOTIFICATIONS_FRIENDING = 6488081;
    public static final int LOAD_TAB_NOTIFICATIONS_FRIENDING_NOANIM = 6488082;
    public static final int LOAD_TAB_NOTIFICATIONS_NOANIM = 6488075;
    public static final short MODULE_ID = 99;
    public static final int NOP_MARKER = 6488078;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "APP_TAB_PERF_FB_FEED";
            case 2:
            case 9:
            case 15:
            case 16:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 3:
                return "APP_TAB_PERF_FB_FRIEND_REQUESTS_TAB";
            case 4:
                return "APP_TAB_PERF_FB_MESSAGES_TAB";
            case 5:
                return "APP_TAB_PERF_FB_NOTIFICATIONS_TAB";
            case 6:
                return "APP_TAB_PERF_FB_BOOKMARKS";
            case 7:
                return "APP_TAB_PERF_LOAD_TAB_MESSAGE";
            case 8:
                return "APP_TAB_PERF_LOAD_TAB_MESSAGE_NOANIM";
            case 10:
                return "APP_TAB_PERF_LOAD_TAB_NOTIFICATIONS";
            case 11:
                return "APP_TAB_PERF_LOAD_TAB_NOTIFICATIONS_NOANIM";
            case 12:
                return "APP_TAB_PERF_LOAD_TAB_BOOKMARK_NOANIM";
            case 13:
                return "APP_TAB_PERF_LOAD_TAB_BOOKMARK";
            case 14:
                return "APP_TAB_PERF_NOP_MARKER";
            case 17:
                return "APP_TAB_PERF_LOAD_TAB_NOTIFICATIONS_FRIENDING";
            case 18:
                return "APP_TAB_PERF_LOAD_TAB_NOTIFICATIONS_FRIENDING_NOANIM";
        }
    }
}
