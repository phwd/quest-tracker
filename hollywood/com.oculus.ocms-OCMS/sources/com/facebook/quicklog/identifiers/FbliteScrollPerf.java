package com.facebook.quicklog.identifiers;

public class FbliteScrollPerf {
    public static final short MODULE_ID = 659;
    public static final int SCROLL_PERF = 43188225;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FBLITE_SCROLL_PERF_SCROLL_PERF";
    }
}
