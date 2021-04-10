package com.facebook.quicklog.identifiers;

public class AlohaUserManagement {
    public static final short MODULE_ID = 1958;
    public static final int SERVICE_API = 128322156;

    public static String getMarkerName(int i) {
        return i != 2668 ? "UNDEFINED_QPL_EVENT" : "ALOHA_USER_MANAGEMENT_SERVICE_API";
    }
}
