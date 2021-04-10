package com.facebook.quicklog.identifiers;

public class RibReliability {
    public static final int ERROR = 944124250;
    public static final int INFO = 944117122;
    public static final short MODULE_ID = 14406;

    public static String getMarkerName(int i) {
        return i != 5506 ? i != 12634 ? "UNDEFINED_QPL_EVENT" : "RIB_RELIABILITY_ERROR" : "RIB_RELIABILITY_INFO";
    }
}
