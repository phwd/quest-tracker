package com.facebook.quicklog.identifiers;

public class PaymentsModules {
    public static final short MODULE_ID = 309;
    public static final int TEST = 20250626;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "PAYMENTS_MODULES_TEST";
    }
}
