package com.facebook.quicklog.identifiers;

public class IgAppPerf {
    public static final int IG_FEED_ASYNC_VIEW_MODEL_UPDATE = 25034759;
    public static final int IG_SHAREDPREFERENCES_APPLY = 25034756;
    public static final int IG_SHAREDPREFERENCES_EDIT = 25034757;
    public static final int IG_SHAREDPREFERENCES_GET = 25034755;
    public static final int IG_SHAREDPREFERENCES_PUT = 25034754;
    public static final int IG_SHAREDPREFERENCES_REMOVE = 25034758;
    public static final short MODULE_ID = 382;

    public static String getMarkerName(int i) {
        switch (i) {
            case 2:
                return "IG_APP_PERF_IG_SHAREDPREFERENCES_PUT";
            case 3:
                return "IG_APP_PERF_IG_SHAREDPREFERENCES_GET";
            case 4:
                return "IG_APP_PERF_IG_SHAREDPREFERENCES_APPLY";
            case 5:
                return "IG_APP_PERF_IG_SHAREDPREFERENCES_EDIT";
            case 6:
                return "IG_APP_PERF_IG_SHAREDPREFERENCES_REMOVE";
            case 7:
                return "IG_APP_PERF_IG_FEED_ASYNC_VIEW_MODEL_UPDATE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
