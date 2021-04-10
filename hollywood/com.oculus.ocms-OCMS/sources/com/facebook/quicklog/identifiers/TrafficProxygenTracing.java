package com.facebook.quicklog.identifiers;

public class TrafficProxygenTracing {
    public static final short MODULE_ID = 769;
    public static final int TEST = 50397185;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "TRAFFIC_PROXYGEN_TRACING_TEST";
    }
}
