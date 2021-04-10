package com.facebook.quicklog.identifiers;

public class LocationProducts {
    public static final int FINDWIFI_DASHBOARD_TTI = 31522817;
    public static final int MESSENGER_ANDROID_LOCATION_SHARING_CORE_NUX_MIGRATION = 31528796;
    public static final short MODULE_ID = 481;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 5980 ? "UNDEFINED_QPL_EVENT" : "LOCATION_PRODUCTS_MESSENGER_ANDROID_LOCATION_SHARING_CORE_NUX_MIGRATION" : "LOCATION_PRODUCTS_FINDWIFI_DASHBOARD_TTI";
    }
}
