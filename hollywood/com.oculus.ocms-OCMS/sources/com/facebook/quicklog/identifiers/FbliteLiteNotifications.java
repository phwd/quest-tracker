package com.facebook.quicklog.identifiers;

public class FbliteLiteNotifications {
    public static final int LITE_TIME_TILL_BADGE = 38862849;
    public static final short MODULE_ID = 593;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FBLITE_LITE_NOTIFICATIONS_LITE_TIME_TILL_BADGE";
    }
}
