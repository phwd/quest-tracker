package com.facebook.quicklog.identifiers;

public class TestAndroidModule {
    public static final int ANDROID_TEST_EVENT_ONE = 13565953;
    public static final int ANDROID_TEST_EVENT_TWO = 13565954;
    public static final short MODULE_ID = 207;
    public static final int TEST_ANDROID_EVENT2 = 13565956;
    public static final int TEST_KAIYUE_01 = 13565973;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 21 ? "UNDEFINED_QPL_EVENT" : "TEST_ANDROID_MODULE_TEST_KAIYUE_01" : "TEST_ANDROID_MODULE_TEST_ANDROID_EVENT2" : "TEST_ANDROID_MODULE_ANDROID_TEST_EVENT_TWO" : "TEST_ANDROID_MODULE_ANDROID_TEST_EVENT_ONE";
    }
}
