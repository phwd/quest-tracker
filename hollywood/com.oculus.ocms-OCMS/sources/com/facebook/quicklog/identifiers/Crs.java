package com.facebook.quicklog.identifiers;

public class Crs {
    public static final int ARTICLE_LOAD_ANDROID = 34144268;
    public static final int BACKGROUND_START_ANDROID = 34144262;
    public static final int FEED_LOAD_ANDROID = 34144266;
    public static final int IAB_LAUNCH_ANDROID = 34144269;
    public static final int MAIN_FEED_E2E_ANDROID = 34144263;
    public static final short MODULE_ID = 521;
    public static final int PERF_COLD_START_ANDROID = 34144260;
    public static final int PERF_WARM_START_ANDROID = 34144261;

    public static String getMarkerName(int i) {
        return i != 4 ? i != 5 ? i != 6 ? i != 7 ? i != 10 ? i != 12 ? i != 13 ? "UNDEFINED_QPL_EVENT" : "CRS_IAB_LAUNCH_ANDROID" : "CRS_ARTICLE_LOAD_ANDROID" : "CRS_FEED_LOAD_ANDROID" : "CRS_MAIN_FEED_E2E_ANDROID" : "CRS_BACKGROUND_START_ANDROID" : "CRS_PERF_WARM_START_ANDROID" : "CRS_PERF_COLD_START_ANDROID";
    }
}
