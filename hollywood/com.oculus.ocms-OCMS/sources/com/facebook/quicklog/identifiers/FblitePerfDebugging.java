package com.facebook.quicklog.identifiers;

public class FblitePerfDebugging {
    public static final int FBLITE_QPL_DEBUG = 37552129;
    public static final short MODULE_ID = 573;
    public static final int TIME_DRIFT = 37552130;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "FBLITE_PERF_DEBUGGING_TIME_DRIFT" : "FBLITE_PERF_DEBUGGING_FBLITE_QPL_DEBUG";
    }
}
