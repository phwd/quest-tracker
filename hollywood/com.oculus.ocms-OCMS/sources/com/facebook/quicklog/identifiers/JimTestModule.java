package com.facebook.quicklog.identifiers;

public class JimTestModule {
    public static final int JIM_TEST_EVENT = 56098817;
    public static final int JIM_TEST_EVENT_2 = 56098818;
    public static final short MODULE_ID = 856;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "JIM_TEST_MODULE_JIM_TEST_EVENT_2" : "JIM_TEST_MODULE_JIM_TEST_EVENT";
    }
}
