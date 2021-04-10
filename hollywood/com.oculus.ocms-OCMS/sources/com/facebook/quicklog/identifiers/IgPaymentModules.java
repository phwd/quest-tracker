package com.facebook.quicklog.identifiers;

public class IgPaymentModules {
    public static final int ADD_PAYMENT_METHOD = 39059460;
    public static final int ADD_SHIPPING_ADDRESS = 39059459;
    public static final int HAS_PAYMENT_SETTINGS_ANDROID = 39059457;
    public static final short MODULE_ID = 596;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "IG_PAYMENT_MODULES_ADD_PAYMENT_METHOD" : "IG_PAYMENT_MODULES_ADD_SHIPPING_ADDRESS" : "IG_PAYMENT_MODULES_HAS_PAYMENT_SETTINGS_ANDROID";
    }
}
