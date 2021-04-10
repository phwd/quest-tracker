package com.facebook.quicklog.identifiers;

public class Bell {
    public static final int B4A_PERF_COLD_START = 29556737;
    public static final int B4A_PERF_COLD_START_FBAPPIMPL_ON_CREATE = 29556738;
    public static final int B4A_PERF_LUKEWARM_START = 29556739;
    public static final int B4A_PERF_WARM_START = 29556740;
    public static final short MODULE_ID = 451;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "BELL_B4A_PERF_WARM_START" : "BELL_B4A_PERF_LUKEWARM_START" : "BELL_B4A_PERF_COLD_START_FBAPPIMPL_ON_CREATE" : "BELL_B4A_PERF_COLD_START";
    }
}
