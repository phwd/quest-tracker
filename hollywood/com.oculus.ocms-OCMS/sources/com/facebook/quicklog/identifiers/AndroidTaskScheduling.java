package com.facebook.quicklog.identifiers;

public class AndroidTaskScheduling {
    public static final short MODULE_ID = 631;
    public static final int THREAD_POOLS_ANDROID = 41353217;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ANDROID_TASK_SCHEDULING_THREAD_POOLS_ANDROID";
    }
}
