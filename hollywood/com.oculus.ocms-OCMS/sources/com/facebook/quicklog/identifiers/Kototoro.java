package com.facebook.quicklog.identifiers;

public class Kototoro {
    public static final int FETCH_FEED = 25231361;
    public static final int KOTOTORO_COLD_START = 25231362;
    public static final int KOTOTORO_FEED_DOWNLOAD_FIRST_TIME = 25231368;
    public static final int KOTOTORO_FEED_VIDEO_DOWNLOAD = 25231363;
    public static final int KOTOTORO_HOT_START = 25231367;
    public static final int KOTOTORO_LOGIN_PERFORMANCE = 25231372;
    public static final int KOTOTORO_WARM_START = 25231366;
    public static final short MODULE_ID = 385;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 6 ? i != 7 ? i != 8 ? i != 12 ? "UNDEFINED_QPL_EVENT" : "KOTOTORO_KOTOTORO_LOGIN_PERFORMANCE" : "KOTOTORO_KOTOTORO_FEED_DOWNLOAD_FIRST_TIME" : "KOTOTORO_KOTOTORO_HOT_START" : "KOTOTORO_KOTOTORO_WARM_START" : "KOTOTORO_KOTOTORO_FEED_VIDEO_DOWNLOAD" : "KOTOTORO_KOTOTORO_COLD_START" : "FEED";
    }
}
