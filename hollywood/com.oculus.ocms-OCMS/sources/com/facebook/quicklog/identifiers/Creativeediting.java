package com.facebook.quicklog.identifiers;

public class Creativeediting {
    public static final int CREATIVEEDITING = 2228225;
    public static final int IMAGE_SIMILARITY = 2228228;
    public static final short MODULE_ID = 34;
    public static final int PERF_INIT_NO_APPLY = 2228227;
    public static final int PERF_INIT_WITH_APPLY = 2228226;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "CREATIVEEDITING_IMAGE_SIMILARITY" : "CREATIVEEDITING_PERF_INIT_NO_APPLY" : "CREATIVEEDITING_PERF_INIT_WITH_APPLY" : "CREATIVEEDITING_CREATIVEEDITING";
    }
}
