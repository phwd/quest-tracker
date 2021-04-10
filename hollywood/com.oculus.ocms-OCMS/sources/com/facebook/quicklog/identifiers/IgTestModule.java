package com.facebook.quicklog.identifiers;

public class IgTestModule {
    public static final short MODULE_ID = 459;
    public static final int SAMPLING_TEST_EVENT = 30081025;
    public static final int SAMPLING_TEST_EVENT_V2 = 30081026;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "IG_TEST_MODULE_SAMPLING_TEST_EVENT_V2" : "IG_TEST_MODULE_SAMPLING_TEST_EVENT";
    }
}
