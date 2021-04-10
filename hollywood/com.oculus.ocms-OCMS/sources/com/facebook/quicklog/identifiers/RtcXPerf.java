package com.facebook.quicklog.identifiers;

public class RtcXPerf {
    public static final short MODULE_ID = 626;
    public static final int START_OUTGOING_CONNECTION = 41025537;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "RTC_X_PERF_START_OUTGOING_CONNECTION";
    }
}
