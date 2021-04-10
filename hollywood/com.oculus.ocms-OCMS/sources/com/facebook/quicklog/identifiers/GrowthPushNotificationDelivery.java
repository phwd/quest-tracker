package com.facebook.quicklog.identifiers;

public class GrowthPushNotificationDelivery {
    public static final short MODULE_ID = 13890;
    public static final int PUSH_NOTIFICATION_DELIVERY_FUNNEL_TEST = 910298668;

    public static String getMarkerName(int i) {
        return i != 3628 ? "UNDEFINED_QPL_EVENT" : "GROWTH_PUSH_NOTIFICATION_DELIVERY_PUSH_NOTIFICATION_DELIVERY_FUNNEL_TEST";
    }
}
