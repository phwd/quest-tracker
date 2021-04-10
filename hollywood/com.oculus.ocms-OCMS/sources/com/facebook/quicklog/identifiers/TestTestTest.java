package com.facebook.quicklog.identifiers;

public class TestTestTest {
    public static final int GINDI_TEST_EVENT_B = 61407234;
    public static final short MODULE_ID = 937;
    public static final int TEST_EVENT_A = 61407233;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "TEST_TEST_TEST_GINDI_TEST_EVENT_B" : "TEST_EVENT_A";
    }
}
