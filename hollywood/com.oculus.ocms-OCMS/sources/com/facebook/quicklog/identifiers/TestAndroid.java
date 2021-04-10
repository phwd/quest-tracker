package com.facebook.quicklog.identifiers;

public class TestAndroid {
    public static final int ALEXN_TEST_REMOVE = 13500424;
    public static final int IMAGE_LOAD = 13500425;
    public static final short MODULE_ID = 206;
    public static final int TEST2 = 13500418;
    public static final int TEST3 = 13500419;
    public static final int TEST4 = 13500422;
    public static final int TEST6 = 13500423;
    public static final int TESTYTEST = 13500417;
    public static final int TEST_5 = 13500421;
    public static final int TEST_FOR_FIXING = 13500420;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "TEST_ANDROID_TESTYTEST";
            case 2:
                return "TEST_ANDROID_TEST2";
            case 3:
                return "TEST_ANDROID_TEST3";
            case 4:
                return "TEST_ANDROID_TEST_FOR_FIXING";
            case 5:
                return "TEST_ANDROID_TEST_5";
            case 6:
                return "TEST_ANDROID_TEST4";
            case 7:
                return "TEST_ANDROID_TEST6";
            case 8:
                return "TEST_ANDROID_ALEXN_TEST_REMOVE";
            case 9:
                return "TEST_ANDROID_IMAGE_LOAD";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
