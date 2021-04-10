package com.facebook.quicklog.identifiers;

public class QplTestFour {
    public static final short MODULE_ID = 981;
    public static final int TEST_EVENT_BOT = 64296835;

    public static String getMarkerName(int i) {
        return i != 6019 ? "UNDEFINED_QPL_EVENT" : "QPL_TEST_FOUR_TEST_EVENT_BOT";
    }
}
