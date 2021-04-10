package com.facebook.quicklog.identifiers;

public class FbliteMessagingPerf {
    public static final int FBLITE_MESSAGING_CACHE_LATENCY = 39321602;
    public static final int FBLITE_MESSAGING_ONTYPING_DURATION = 39329540;
    public static final int MESSAGE_RECEIVE_FBLITE = 39321601;
    public static final short MODULE_ID = 600;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 7940 ? "UNDEFINED_QPL_EVENT" : "FBLITE_MESSAGING_PERF_FBLITE_MESSAGING_ONTYPING_DURATION" : "FBLITE_MESSAGING_PERF_FBLITE_MESSAGING_CACHE_LATENCY" : "FBLITE_MESSAGING_PERF_MESSAGE_RECEIVE_FBLITE";
    }
}
