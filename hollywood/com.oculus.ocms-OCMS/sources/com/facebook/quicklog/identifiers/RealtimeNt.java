package com.facebook.quicklog.identifiers;

public class RealtimeNt {
    public static final short MODULE_ID = 893;
    public static final int SUBSCRIPTION_ACTIVE = 58523649;
    public static final int VIEW_ATTACHED = 58523650;
    public static final int VIEW_MOUNTED = 58523652;
    public static final int VIEW_VISIBLE = 58523651;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "REALTIME_NT_VIEW_MOUNTED" : "REALTIME_NT_VIEW_VISIBLE" : "REALTIME_NT_VIEW_ATTACHED" : "REALTIME_NT_SUBSCRIPTION_ACTIVE";
    }
}
