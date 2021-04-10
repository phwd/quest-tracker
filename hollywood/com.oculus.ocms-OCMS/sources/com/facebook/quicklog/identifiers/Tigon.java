package com.facebook.quicklog.identifiers;

public class Tigon {
    public static final int BENCHMARK_ITERATION = 17956868;
    public static final int BENCHMARK_RUN = 17956866;
    public static final short MODULE_ID = 274;
    public static final int PRE_REQUEST_SEND_CALL = 17956867;
    public static final int REQUEST_EXECUTION = 17956869;
    public static final int REQUEST_EXECUTION_AGGREGATED = 17961302;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 4438 ? "UNDEFINED_QPL_EVENT" : "TIGON_REQUEST_EXECUTION_AGGREGATED" : "TIGON_REQUEST_EXECUTION" : "TIGON_BENCHMARK_ITERATION" : "TIGON_PRE_REQUEST_SEND_CALL" : "TIGON_BENCHMARK_RUN";
    }
}
