package com.facebook.quicklog.identifiers;

public class WpAndroidNotifications {
    public static final int FILTER_NOTIFICATIONS = 53477377;
    public static final short MODULE_ID = 816;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "WP_ANDROID_NOTIFICATIONS_FILTER_NOTIFICATIONS";
    }
}
