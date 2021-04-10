package com.facebook.quicklog.identifiers;

public class ConditionalWorker {
    public static final int CALL = 2883585;
    public static final short MODULE_ID = 44;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CONDITIONAL_WORKER_CALL";
    }
}
