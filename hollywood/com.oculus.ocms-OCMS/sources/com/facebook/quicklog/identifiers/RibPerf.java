package com.facebook.quicklog.identifiers;

public class RibPerf {
    public static final int JOIN_ROOM = 648282113;
    public static final short MODULE_ID = 9892;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "RIB_PERF_JOIN_ROOM";
    }
}
