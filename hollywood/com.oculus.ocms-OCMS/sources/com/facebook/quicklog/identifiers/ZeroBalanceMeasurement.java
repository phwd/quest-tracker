package com.facebook.quicklog.identifiers;

public class ZeroBalanceMeasurement {
    public static final int FBLITE_ZERO_BALANCE_DETECTION = 56164354;
    public static final int FBLITE_ZERO_BALANCE_FIX = 56164353;
    public static final int FBLITE_ZERO_TIMEOUT_DETECTION = 56189915;
    public static final short MODULE_ID = 857;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 25563 ? "UNDEFINED_QPL_EVENT" : "ZERO_BALANCE_MEASUREMENT_FBLITE_ZERO_TIMEOUT_DETECTION" : "ZERO_BALANCE_MEASUREMENT_FBLITE_ZERO_BALANCE_DETECTION" : "ZERO_BALANCE_MEASUREMENT_FBLITE_ZERO_BALANCE_FIX";
    }
}
