package com.facebook.quicklog.identifiers;

public class WorkspeedThreadview {
    public static final short MODULE_ID = 630;
    public static final int THREAD_VIEW_START = 41287681;
    public static final int WORKSPEED_THREAD_VIEW_LOAD_ANDROID = 41287682;
    public static final int WORKSPEED_THREAD_VIEW_NEXT_PAGE_ANDROID = 41287683;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "WORKSPEED_THREADVIEW_WORKSPEED_THREAD_VIEW_NEXT_PAGE_ANDROID" : "WORKSPEED_THREADVIEW_WORKSPEED_THREAD_VIEW_LOAD_ANDROID" : "WORKSPEED_THREADVIEW_THREAD_VIEW_START";
    }
}
