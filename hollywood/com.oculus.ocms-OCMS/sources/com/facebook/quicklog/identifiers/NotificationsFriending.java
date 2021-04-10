package com.facebook.quicklog.identifiers;

public class NotificationsFriending {
    public static final short MODULE_ID = 145;
    public static final int NOTIF_LIST_LOAD_TIME_COLD = 9502729;
    public static final int NOTIF_LIST_LOAD_TIME_WARM = 9502730;
    public static final int NOTIF_SCROLL_LOAD = 9502731;
    public static final int PULL_TO_REFRESH_LOAD_TIME = 9502725;

    public static String getMarkerName(int i) {
        if (i == 5) {
            return "NOTIFICATIONS_FRIENDING_PULL_TO_REFRESH_LOAD_TIME";
        }
        switch (i) {
            case 9:
                return "NOTIFICATIONS_FRIENDING_NOTIF_LIST_LOAD_TIME_COLD";
            case 10:
                return "NOTIFICATIONS_FRIENDING_NOTIF_LIST_LOAD_TIME_WARM";
            case 11:
                return "NOTIFICATIONS_FRIENDING_NOTIF_SCROLL_LOAD";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
