package com.facebook.quicklog.identifiers;

public class Stax {
    public static final short MODULE_ID = 5250;
    public static final int THREADLIST_TO_THREADVIEW = 344075179;

    public static String getMarkerName(int i) {
        return i != 11179 ? "UNDEFINED_QPL_EVENT" : "STAX_THREADLIST_TO_THREADVIEW";
    }
}
