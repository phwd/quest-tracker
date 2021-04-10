package com.facebook.quicklog.identifiers;

public class BizappStartupPerf {
    public static final int ANDROID_COLD_START = 43122690;
    public static final int ANDROID_WARM_START = 43122691;
    public static final short MODULE_ID = 658;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "BIZAPP_STARTUP_PERF_ANDROID_WARM_START" : "BIZAPP_STARTUP_PERF_ANDROID_COLD_START";
    }
}
