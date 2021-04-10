package com.facebook.quicklog.identifiers;

public class BusinessInboxNotification {
    public static final int BIIM_NOTIF_FLOW = 59965441;
    public static final short MODULE_ID = 915;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "BUSINESS_INBOX_NOTIFICATION_BIIM_NOTIF_FLOW";
    }
}
