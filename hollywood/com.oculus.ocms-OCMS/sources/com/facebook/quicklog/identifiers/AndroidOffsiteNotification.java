package com.facebook.quicklog.identifiers;

public class AndroidOffsiteNotification {
    public static final int LOGGED_OUT_PUSH_NOTIFICATION_FUNNEL = 710613709;
    public static final short MODULE_ID = 10843;
    public static final int PUSH_NOTIFICATION_DELIVERY_FUNNEL = 710610308;

    public static String getMarkerName(int i) {
        return i != 3460 ? i != 6861 ? "UNDEFINED_QPL_EVENT" : "ANDROID_OFFSITE_NOTIFICATION_LOGGED_OUT_PUSH_NOTIFICATION_FUNNEL" : "ANDROID_OFFSITE_NOTIFICATION_PUSH_NOTIFICATION_DELIVERY_FUNNEL";
    }
}
