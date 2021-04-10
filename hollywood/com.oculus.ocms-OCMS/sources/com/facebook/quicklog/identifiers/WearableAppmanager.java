package com.facebook.quicklog.identifiers;

public class WearableAppmanager {
    public static final int APP_INSTALL = 398919239;
    public static final int COLD_START = 398922866;
    public static final short MODULE_ID = 6087;

    public static String getMarkerName(int i) {
        return i != 1607 ? i != 5234 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_APPMANAGER_COLD_START" : "WEARABLE_APPMANAGER_APP_INSTALL";
    }
}
