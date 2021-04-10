package com.facebook.quicklog.identifiers;

public class LocalSurfaceAndroidPerf {
    public static final int CONTENT_TTI = 17235969;
    public static final int HEADER_TTI = 17235970;
    public static final int MAP_TTI = 17235971;
    public static final short MODULE_ID = 263;
    public static final int SEARCH_RESULTS_TTI = 17235973;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "LOCAL_SURFACE_ANDROID_PERF_SEARCH_RESULTS_TTI" : "LOCAL_SURFACE_ANDROID_PERF_MAP_TTI" : "LOCAL_SURFACE_ANDROID_PERF_HEADER_TTI" : "LOCAL_SURFACE_ANDROID_PERF_CONTENT_TTI";
    }
}
