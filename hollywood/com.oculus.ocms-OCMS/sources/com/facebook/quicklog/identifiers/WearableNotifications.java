package com.facebook.quicklog.identifiers;

public class WearableNotifications {
    public static final int CLIENT_NOTIFICATION_PARSING = 267129988;
    public static final int CLIENT_NOTIFICATION_PING_RENDERING = 267137428;
    public static final short MODULE_ID = 4076;
    public static final int SERVICE_PUSH_DELIVERY = 267126027;

    public static String getMarkerName(int i) {
        return i != 1291 ? i != 5252 ? i != 12692 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_NOTIFICATIONS_CLIENT_NOTIFICATION_PING_RENDERING" : "WEARABLE_NOTIFICATIONS_CLIENT_NOTIFICATION_PARSING" : "WEARABLE_NOTIFICATIONS_SERVICE_PUSH_DELIVERY";
    }
}
