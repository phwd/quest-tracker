package com.facebook.quicklog.identifiers;

public class Diagnostics {
    public static final short MODULE_ID = 712;
    public static final int RELIABILITY_PATTERNS = 46661634;
    public static final int SESSION_DIAGNOSTICS = 46661633;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "DIAGNOSTICS_RELIABILITY_PATTERNS" : "DIAGNOSTICS_SESSION_DIAGNOSTICS";
    }
}
