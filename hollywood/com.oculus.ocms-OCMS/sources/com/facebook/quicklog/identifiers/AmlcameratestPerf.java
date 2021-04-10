package com.facebook.quicklog.identifiers;

public class AmlcameratestPerf {
    public static final int DELAY = 14417921;
    public static final short MODULE_ID = 220;
    public static final int USER = 14417924;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "AMLCAMERATEST_PERF_USER" : "AMLCAMERATEST_PERF_DELAY";
    }
}
