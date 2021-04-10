package com.facebook.quicklog.identifiers;

public class IgNetworkPerf {
    public static final int BANDWIDTH_CALCULATION = 25624580;
    public static final short MODULE_ID = 391;
    public static final int PAYLOAD_SCHEDULE_CALCULATION = 25624582;
    public static final int QUEUE_TIME = 25624577;
    public static final int REQUEST_ADDED = 25624578;
    public static final int REQUEST_CAP_BANDWIDTH_CALCULATION = 25624583;
    public static final int REQUEST_DISPATCHED = 25624579;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 6 ? i != 7 ? "UNDEFINED_QPL_EVENT" : "IG_NETWORK_PERF_REQUEST_CAP_BANDWIDTH_CALCULATION" : "IG_NETWORK_PERF_PAYLOAD_SCHEDULE_CALCULATION" : "IG_NETWORK_PERF_BANDWIDTH_CALCULATION" : "IG_NETWORK_PERF_REQUEST_DISPATCHED" : "IG_NETWORK_PERF_REQUEST_ADDED" : "IG_NETWORK_PERF_QUEUE_TIME";
    }
}
