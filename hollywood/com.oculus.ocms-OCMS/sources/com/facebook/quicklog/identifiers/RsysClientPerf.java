package com.facebook.quicklog.identifiers;

public class RsysClientPerf {
    public static final int ACTION_EXECUTION = 51576836;
    public static final int ACTION_TIME_IN_QUEUE = 51576835;
    public static final int BRIDGE_EXECUTION = 51576834;
    public static final int EXECUTE_ALL_REDUCER_FOR_ACTION = 51576833;
    public static final int INCOMING_CALL_FLOW = 51576838;
    public static final short MODULE_ID = 787;
    public static final int OUTGOING_CALL_FLOW = 51576837;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "RSYS_CLIENT_PERF_EXECUTE_ALL_REDUCER_FOR_ACTION";
            case 2:
                return "RSYS_CLIENT_PERF_BRIDGE_EXECUTION";
            case 3:
                return "RSYS_CLIENT_PERF_ACTION_TIME_IN_QUEUE";
            case 4:
                return "RSYS_CLIENT_PERF_ACTION_EXECUTION";
            case 5:
                return "RSYS_CLIENT_PERF_OUTGOING_CALL_FLOW";
            case 6:
                return "RSYS_CLIENT_PERF_INCOMING_CALL_FLOW";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
