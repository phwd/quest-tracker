package com.facebook.quicklog.identifiers;

public class GrowthPushDelivery {
    public static final short MODULE_ID = 16348;
    public static final int PUSH_NOTIFICATION_DELIVERY_FUNNEL_TEST = 1071388373;

    public static String getMarkerName(int i) {
        return i != 5845 ? "UNDEFINED_QPL_EVENT" : "GROWTH_PUSH_DELIVERY_PUSH_NOTIFICATION_DELIVERY_FUNNEL_TEST";
    }
}
