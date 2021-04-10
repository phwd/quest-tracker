package com.facebook.quicklog.identifiers;

public class IgAndroidViewpagerPerf {
    public static final int BIND_VIEW = 539637512;
    public static final int CREATE_VIEW = 539624923;
    public static final short MODULE_ID = 8234;

    public static String getMarkerName(int i) {
        return i != 1499 ? i != 14088 ? "UNDEFINED_QPL_EVENT" : "IG_ANDROID_VIEWPAGER_PERF_BIND_VIEW" : "IG_ANDROID_VIEWPAGER_PERF_CREATE_VIEW";
    }
}
