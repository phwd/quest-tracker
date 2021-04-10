package com.facebook.quicklog.identifiers;

public class WearableCommsNotification {
    public static final short MODULE_ID = 4385;
    public static final int NOTIFICATION_PING_TTRC = 287387301;
    public static final int NOTIF_TO_THREAD_VIEW_TTRC = 287391631;

    public static String getMarkerName(int i) {
        return i != 11941 ? i != 16271 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_COMMS_NOTIFICATION_NOTIF_TO_THREAD_VIEW_TTRC" : "WEARABLE_COMMS_NOTIFICATION_NOTIFICATION_PING_TTRC";
    }
}
