package com.facebook.quicklog.identifiers;

public class TestModuleOne {
    public static final short MODULE_ID = 8;
    public static final int TEST_EVENT_FOUR = 524306;
    public static final int TEST_EVENT_ONE = 524300;
    public static final int TEST_EVENT_THREE = 524305;
    public static final int TEST_EVENT_TWO = 524304;

    public static String getMarkerName(int i) {
        if (i == 12) {
            return "TEST_MODULE_ONE_TEST_EVENT_ONE";
        }
        switch (i) {
            case 16:
                return "TEST_MODULE_ONE_TEST_EVENT_TWO";
            case 17:
                return "TEST_MODULE_ONE_TEST_EVENT_THREE";
            case 18:
                return "TEST_MODULE_ONE_TEST_EVENT_FOUR";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
