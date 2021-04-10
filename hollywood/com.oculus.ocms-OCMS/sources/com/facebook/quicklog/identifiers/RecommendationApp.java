package com.facebook.quicklog.identifiers;

public class RecommendationApp {
    public static final int BACKGROUND_START = 31916036;
    public static final int MAIN_FEED_E2E = 31916037;
    public static final int MAIN_FEED_PTR = 31916038;
    public static final short MODULE_ID = 487;
    public static final int PERF_COLD_START_ANDROID = 31916033;
    public static final int PERF_LUKEWARM_START_ANDROID = 31916034;
    public static final int PERF_WARM_START_ANDROID = 31916035;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "RECOMMENDATION_APP_PERF_COLD_START_ANDROID";
            case 2:
                return "RECOMMENDATION_APP_PERF_LUKEWARM_START_ANDROID";
            case 3:
                return "RECOMMENDATION_APP_PERF_WARM_START_ANDROID";
            case 4:
                return "RECOMMENDATION_APP_BACKGROUND_START";
            case 5:
                return "RECOMMENDATION_APP_MAIN_FEED_E2E";
            case 6:
                return "RECOMMENDATION_APP_MAIN_FEED_PTR";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
