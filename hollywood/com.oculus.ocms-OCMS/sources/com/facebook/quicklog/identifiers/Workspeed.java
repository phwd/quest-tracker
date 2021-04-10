package com.facebook.quicklog.identifiers;

public class Workspeed {
    public static final short MODULE_ID = 680;
    public static final int STARTUP = 44564481;
    public static final int THREADLIST_TO_THREADVIEW = 44564482;
    public static final int THREAD_LIST_SCROLL = 44564485;
    public static final int THREAD_VIEW_SCROLL = 44564486;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 5 ? i != 6 ? "UNDEFINED_QPL_EVENT" : "WORKSPEED_THREAD_VIEW_SCROLL" : "WORKSPEED_THREAD_LIST_SCROLL" : "WORKSPEED_THREADLIST_TO_THREADVIEW" : "WORKSPEED_STARTUP";
    }
}
