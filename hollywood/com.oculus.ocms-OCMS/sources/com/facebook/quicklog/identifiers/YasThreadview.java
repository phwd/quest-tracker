package com.facebook.quicklog.identifiers;

public class YasThreadview {
    public static final short MODULE_ID = 471;
    public static final int PREPARE_VIDEO = 30867457;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "YAS_THREADVIEW_PREPARE_VIDEO";
    }
}
