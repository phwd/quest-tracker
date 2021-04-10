package com.facebook.quicklog.identifiers;

public class WpWwwUserIntegrations {
    public static final int DESKTOP_OAUTH_LOGIN = 59244546;
    public static final short MODULE_ID = 904;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "WP_WWW_USER_INTEGRATIONS_DESKTOP_OAUTH_LOGIN";
    }
}
