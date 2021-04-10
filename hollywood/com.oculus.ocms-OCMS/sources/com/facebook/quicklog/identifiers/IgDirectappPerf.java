package com.facebook.quicklog.identifiers;

public class IgDirectappPerf {
    public static final int APP_START = 29622273;
    public static final short MODULE_ID = 452;
    public static final int THREAD_FROM_NOTIFICATION = 29622275;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "IG_DIRECTAPP_PERF_THREAD_FROM_NOTIFICATION" : "IG_DIRECTAPP_PERF_APP_START";
    }
}
