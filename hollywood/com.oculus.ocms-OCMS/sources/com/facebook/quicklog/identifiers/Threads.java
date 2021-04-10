package com.facebook.quicklog.identifiers;

public class Threads {
    public static final int CREATE_THREAD_LIST = 3538945;
    public static final short MODULE_ID = 54;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "THREADS_CREATE_THREAD_LIST";
    }
}
