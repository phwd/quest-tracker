package com.facebook.quicklog.identifiers;

public class CreatorAppAndroidPerf {
    public static final int COLD_START = 21233665;
    public static final int COLD_START_LOGIN = 21233666;
    public static final short MODULE_ID = 324;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "CREATOR_APP_ANDROID_PERF_COLD_START_LOGIN" : "CREATOR_APP_ANDROID_PERF_COLD_START";
    }
}
