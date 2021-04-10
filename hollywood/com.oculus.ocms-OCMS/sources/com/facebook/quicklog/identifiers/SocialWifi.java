package com.facebook.quicklog.identifiers;

public class SocialWifi {
    public static final short MODULE_ID = 12019;
    public static final int SOCIAL_WIFI_FB4A_TEST = 787688181;

    public static String getMarkerName(int i) {
        return i != 10997 ? "UNDEFINED_QPL_EVENT" : "SOCIAL_WIFI_SOCIAL_WIFI_FB4A_TEST";
    }
}
