package com.facebook.quicklog.identifiers;

public class BizappFeaturePerf {
    public static final int ANDROID_ADS_MANAGEMENT_TTRC = 45809666;
    public static final int ANDROID_APPOINTMENTS_TTRC = 45809685;
    public static final int ANDROID_COMPOSER_TTRC = 45809670;
    public static final int ANDROID_EVENTS_TTRC = 45809672;
    public static final int ANDROID_INSIGHTS_TTRC = 45809673;
    public static final int ANDROID_JOBS_TTRC = 45809674;
    public static final int ANDROID_NT_TAB_TTRC = 45809665;
    public static final int ANDROID_PHOTOS_TTRC = 45809671;
    public static final int BIZAPP_ANDROID_POST_TTRC = 45809686;
    public static final int BIZAPP_IOS_INSIGHTS_TTRC = 45809681;
    public static final int BIZAPP_IOS_JOBS_TTRC = 45809675;
    public static final short MODULE_ID = 699;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "BIZAPP_FEATURE_PERF_ANDROID_NT_TAB_TTRC";
        }
        if (i == 2) {
            return "BIZAPP_FEATURE_PERF_ANDROID_ADS_MANAGEMENT_TTRC";
        }
        if (i == 17) {
            return "BIZAPP_FEATURE_PERF_BIZAPP_IOS_INSIGHTS_TTRC";
        }
        if (i == 21) {
            return "BIZAPP_FEATURE_PERF_ANDROID_APPOINTMENTS_TTRC";
        }
        if (i == 22) {
            return "BIZAPP_FEATURE_PERF_BIZAPP_ANDROID_POST_TTRC";
        }
        switch (i) {
            case 6:
                return "BIZAPP_FEATURE_PERF_ANDROID_COMPOSER_TTRC";
            case 7:
                return "BIZAPP_FEATURE_PERF_ANDROID_PHOTOS_TTRC";
            case 8:
                return "BIZAPP_FEATURE_PERF_ANDROID_EVENTS_TTRC";
            case 9:
                return "BIZAPP_FEATURE_PERF_ANDROID_INSIGHTS_TTRC";
            case 10:
                return "BIZAPP_FEATURE_PERF_ANDROID_JOBS_TTRC";
            case 11:
                return "BIZAPP_FEATURE_PERF_BIZAPP_IOS_JOBS_TTRC";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
