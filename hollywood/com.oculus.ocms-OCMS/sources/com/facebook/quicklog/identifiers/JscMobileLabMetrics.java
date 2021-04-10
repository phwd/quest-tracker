package com.facebook.quicklog.identifiers;

public class JscMobileLabMetrics {
    public static final int MEASURE_MEMORY = 17367041;
    public static final int MEASURE_MEMORY_DESTROYED = 17367043;
    public static final int MEASURE_MEMORY_UNLOADED = 17367042;
    public static final short MODULE_ID = 265;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "JSC_MOBILE_LAB_METRICS_MEASURE_MEMORY_DESTROYED" : "JSC_MOBILE_LAB_METRICS_MEASURE_MEMORY_UNLOADED" : "JSC_MOBILE_LAB_METRICS_MEASURE_MEMORY";
    }
}
