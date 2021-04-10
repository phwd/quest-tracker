package com.facebook.quicklog.identifiers;

public class WiktorkTest {
    public static final int HELLO_DARKNESS_MY_OLD_FRIEND = 26804236;
    public static final short MODULE_ID = 409;
    public static final int TEST123 = 26804232;
    public static final int WIKTORK_EVENT_TWOB = 26804226;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 8 ? i != 12 ? "UNDEFINED_QPL_EVENT" : "WIKTORK_TEST_HELLO_DARKNESS_MY_OLD_FRIEND" : "WIKTORK_TEST_TEST123" : "hehe";
    }
}
