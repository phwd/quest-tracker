package com.facebook.quicklog.identifiers;

public class Usability {
    public static final int FCR = 42799231;
    public static final short MODULE_ID = 653;
    public static final int USER_TASK = 42795009;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 4223 ? "UNDEFINED_QPL_EVENT" : "USABILITY_FCR" : "USABILITY_USER_TASK";
    }
}
