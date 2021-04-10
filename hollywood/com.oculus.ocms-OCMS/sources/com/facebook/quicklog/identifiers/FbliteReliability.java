package com.facebook.quicklog.identifiers;

public class FbliteReliability {
    public static final int FBLITE_ERROR_SCREENS = 37486593;
    public static final short MODULE_ID = 572;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FBLITE_RELIABILITY_FBLITE_ERROR_SCREENS";
    }
}
