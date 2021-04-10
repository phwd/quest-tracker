package com.facebook.quicklog.identifiers;

public class DashFeed {
    public static final int BAUBLE_SHOW = 2949133;
    public static final int BAUBLE_SHOW_LOGGED_OUT = 2949134;
    public static final int DASH_ACTIVITY_COLD_START = 2949131;
    public static final int DASH_ACTIVITY_LAUNCH_APP_LEAVE = 2949140;
    public static final int DASH_ACTIVITY_MESSAGES_LEAVE = 2949141;
    public static final int DASH_ACTIVITY_MESSAGE_LEAVE = 2949139;
    public static final int DASH_ACTIVITY_NOTIFICATION_LEAVE = 2949138;
    public static final int DASH_ACTIVITY_WARM_START = 2949132;
    public static final int DASH_COLD_START = 2949135;
    public static final int GET_IMAGE_PERF_MARKER = 2949128;
    public static final int LOAD_APPS_FROM_PACKAGE_MANAGER = 2949136;
    public static final int LOAD_NEWER_STORIES_SERVER = 2949121;
    public static final int LOAD_NEWER_STORIES_SERVER_PERF_MARKER = 2949126;
    public static final int LOAD_OLDER_STORIES_CACHE = 2949123;
    public static final int LOAD_OLDER_STORIES_CACHE_PERF_MARKER = 2949125;
    public static final int LOAD_OLDER_STORIES_SERVER = 2949122;
    public static final int LOAD_OLDER_STORIES_SERVER_PERF_MARKER = 2949124;
    public static final int LOAD_SHORTCUTS_FROM_DATABASE = 2949137;
    public static final short MODULE_ID = 45;
    public static final int PERF_BITMAP_SET_TIME = 2949127;
    public static final int RERANK_ALL_PERF_MARKER = 2949130;
    public static final int UPDATE_IMPORTANCE_PERF_MARKER = 2949129;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "DASH_FEED_LOAD_NEWER_STORIES_SERVER";
            case 2:
                return "DASH_FEED_LOAD_OLDER_STORIES_SERVER";
            case 3:
                return "DASH_FEED_LOAD_OLDER_STORIES_CACHE";
            case 4:
                return "DASH_FEED_LOAD_OLDER_STORIES_SERVER_PERF_MARKER";
            case 5:
                return "DASH_FEED_LOAD_OLDER_STORIES_CACHE_PERF_MARKER";
            case 6:
                return "DASH_FEED_LOAD_NEWER_STORIES_SERVER_PERF_MARKER";
            case 7:
                return "DASH_FEED_PERF_BITMAP_SET_TIME";
            case 8:
                return "DASH_FEED_GET_IMAGE_PERF_MARKER";
            case 9:
                return "DASH_FEED_UPDATE_IMPORTANCE_PERF_MARKER";
            case 10:
                return "DASH_FEED_RERANK_ALL_PERF_MARKER";
            case 11:
                return "DASH_FEED_DASH_ACTIVITY_COLD_START";
            case 12:
                return "DASH_FEED_DASH_ACTIVITY_WARM_START";
            case 13:
                return "DASH_FEED_BAUBLE_SHOW";
            case 14:
                return "DASH_FEED_BAUBLE_SHOW_LOGGED_OUT";
            case 15:
                return "DASH_FEED_DASH_COLD_START";
            case 16:
                return "DASH_FEED_LOAD_APPS_FROM_PACKAGE_MANAGER";
            case 17:
                return "DASH_FEED_LOAD_SHORTCUTS_FROM_DATABASE";
            case 18:
                return "DASH_FEED_DASH_ACTIVITY_NOTIFICATION_LEAVE";
            case 19:
                return "DASH_FEED_DASH_ACTIVITY_MESSAGE_LEAVE";
            case 20:
                return "DASH_FEED_DASH_ACTIVITY_LAUNCH_APP_LEAVE";
            case 21:
                return "DASH_FEED_DASH_ACTIVITY_MESSAGES_LEAVE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
