package com.facebook.quicklog.identifiers;

public class IgBoomerangPerf {
    public static final int APP_START = 31064065;
    public static final short MODULE_ID = 474;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_BOOMERANG_PERF_APP_START";
    }
}
