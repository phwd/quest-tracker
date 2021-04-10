package com.facebook.quicklog.identifiers;

public class AndroidNotifications {
    public static final int FILTER_NOTIFICATIONS = 53936129;
    public static final short MODULE_ID = 823;
    public static final int OPEN_NOTIFICATION_SETTINGS = 53936130;
    public static final int PUSH_NOTIFICATION_DELIVERY_FUNNEL_TEST = 53952229;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 16101 ? "UNDEFINED_QPL_EVENT" : "ANDROID_NOTIFICATIONS_PUSH_NOTIFICATION_DELIVERY_FUNNEL_TEST" : "ANDROID_NOTIFICATIONS_OPEN_NOTIFICATION_SETTINGS" : "ANDROID_NOTIFICATIONS_FILTER_NOTIFICATIONS";
    }
}
