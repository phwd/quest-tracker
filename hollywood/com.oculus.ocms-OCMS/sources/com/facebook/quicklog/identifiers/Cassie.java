package com.facebook.quicklog.identifiers;

public class Cassie {
    public static final short MODULE_ID = 143;
    public static final int PERFORM_QUERY = 9371649;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CASSIE_PERFORM_QUERY";
    }
}
