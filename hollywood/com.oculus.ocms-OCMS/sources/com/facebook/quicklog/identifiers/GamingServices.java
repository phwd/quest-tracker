package com.facebook.quicklog.identifiers;

public class GamingServices {
    public static final int GAMING_LOGIN_CLOUD_TTRC = 58276983;
    public static final int GAMING_LOGIN_TTRC = 58261505;
    public static final short MODULE_ID = 889;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 15479 ? "UNDEFINED_QPL_EVENT" : "GAMING_SERVICES_GAMING_LOGIN_CLOUD_TTRC" : "GAMING_SERVICES_GAMING_LOGIN_TTRC";
    }
}
