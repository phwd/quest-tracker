package com.facebook.quicklog.identifiers;

public class QplTestThree {
    public static final short MODULE_ID = 945;
    public static final int TEST = 61931521;
    public static final int TEST_ONE = 61931522;
    public static final int TEST_TEST_ONE = 61931523;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "QPL_TEST_THREE_TEST_TEST_ONE" : "QPL_TEST_THREE_TEST_ONE" : "QPL_TEST_THREE_TEST";
    }
}
