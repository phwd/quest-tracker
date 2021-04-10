package com.facebook.quicklog.identifiers;

public class TransientAnalysis {
    public static final int COLD_START = 33292289;
    public static final int HOT_START = 33292291;
    public static final short MODULE_ID = 508;
    public static final int WARM_START = 33292290;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "TRANSIENT_ANALYSIS_HOT_START" : "TRANSIENT_ANALYSIS_WARM_START" : "TRANSIENT_ANALYSIS_COLD_START";
    }
}
