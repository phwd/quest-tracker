package com.facebook.quicklog.identifiers;

public class Phoneapp {
    public static final int MARKER_GET_TELEPHONY_MANAGER = 6422530;
    public static final int MARKER_SETUP_CALL_LISTENER = 6422529;
    public static final short MODULE_ID = 98;
    public static final int REPLACE_LINEAR_ALLOC_BUFFER = 6422532;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "PHONEAPP_REPLACE_LINEAR_ALLOC_BUFFER" : "PHONEAPP_MARKER_GET_TELEPHONY_MANAGER" : "PHONEAPP_MARKER_SETUP_CALL_LISTENER";
    }
}
