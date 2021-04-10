package com.facebook.quicklog.identifiers;

public class TestModuleTwo {
    public static final short MODULE_ID = 9;
    public static final int TEST_EVENT_FOUR = 589839;
    public static final int TEST_EVENT_ONE = 589843;
    public static final int TEST_EVENT_THREE = 589838;
    public static final int TEST_EVENT_TWO = 589837;

    public static String getMarkerName(int i) {
        if (i == 19) {
            return "TEST_MODULE_TWO_TEST_EVENT_ONE";
        }
        switch (i) {
            case 13:
                return "TEST_MODULE_TWO_TEST_EVENT_TWO";
            case 14:
                return "TEST_MODULE_TWO_TEST_EVENT_THREE";
            case 15:
                return "TEST_MODULE_TWO_TEST_EVENT_FOUR";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
