package com.facebook.quicklog.identifiers;

public class ThreadList {
    public static final int CREATE_MESSAGE_VIEW = 3604481;
    public static final int CREATE_THREAD_LIST = 3604482;
    public static final short MODULE_ID = 55;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "THREAD_LIST_CREATE_THREAD_LIST" : "THREAD_LIST_CREATE_MESSAGE_VIEW";
    }
}
