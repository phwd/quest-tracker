package com.facebook.quicklog.identifiers;

public class HygracewuTest {
    public static final short MODULE_ID = 586;
    public static final int TEST_METRIC_DOCTOR = 38404097;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "HYGRACEWU_TEST_TEST_METRIC_DOCTOR";
    }
}
