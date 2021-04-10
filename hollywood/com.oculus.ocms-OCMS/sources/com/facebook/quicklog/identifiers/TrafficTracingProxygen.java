package com.facebook.quicklog.identifiers;

public class TrafficTracingProxygen {
    public static final short MODULE_ID = 773;
    public static final int OVERALL = 50659331;
    public static final int TEST1 = 50659330;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "TRAFFIC_TRACING_PROXYGEN_OVERALL" : "TRAFFIC_TRACING_PROXYGEN_TEST1";
    }
}
