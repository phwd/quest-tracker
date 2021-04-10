package com.facebook.quicklog.identifiers;

public class SessionInitialization {
    public static final short MODULE_ID = 168;
    public static final int PROF_ERR_STACK_OVERFLOWS = 11010050;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "SESSION_INITIALIZATION_PROF_ERR_STACK_OVERFLOWS";
    }
}
