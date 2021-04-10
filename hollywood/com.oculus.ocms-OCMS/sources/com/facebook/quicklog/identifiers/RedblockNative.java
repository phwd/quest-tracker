package com.facebook.quicklog.identifiers;

public class RedblockNative {
    public static final int EVALUATIONS = 45219841;
    public static final short MODULE_ID = 690;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "REDBLOCK_NATIVE_EVALUATIONS";
    }
}
