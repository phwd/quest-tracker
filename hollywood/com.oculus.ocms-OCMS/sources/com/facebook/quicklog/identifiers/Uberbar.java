package com.facebook.quicklog.identifiers;

public class Uberbar {
    public static final int LOCAL_RESULTS_FETCH = 1769474;
    public static final short MODULE_ID = 27;
    public static final int NETWORK_RESULT_FETCH = 1769473;
    public static final int REMOTE_FETCH = 1769476;
    public static final int REMOTE_FETCH_TL_PREFETCH = 1769475;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "UBERBAR_REMOTE_FETCH" : "UBERBAR_REMOTE_FETCH_TL_PREFETCH" : "UBERBAR_LOCAL_RESULTS_FETCH" : "UBERBAR_NETWORK_RESULT_FETCH";
    }
}
