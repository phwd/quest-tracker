package com.facebook.quicklog.identifiers;

public class RpCopresence {
    public static final short MODULE_ID = 8741;
    public static final int RP_COPRESENCE_DROPIN = 572861632;

    public static String getMarkerName(int i) {
        return i != 11456 ? "UNDEFINED_QPL_EVENT" : "RP_COPRESENCE_RP_COPRESENCE_DROPIN";
    }
}
