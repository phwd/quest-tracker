package com.facebook.quicklog.identifiers;

public class BusinessInboxNotif {
    public static final short MODULE_ID = 940;
    public static final int NOTIF_TRACE = 61603841;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "BUSINESS_INBOX_NOTIF_NOTIF_TRACE";
    }
}
