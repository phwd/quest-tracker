package com.facebook.quicklog.identifiers;

public class WpMobileAdmin {
    public static final int ACTIVATE_USER_SINGLE = 55705601;
    public static final int DEACTIVATE_USER_SINGLE = 55705602;
    public static final short MODULE_ID = 850;
    public static final int OPEN_REPORTED_CONTENT_LIST = 55705603;
    public static final int REPORTED_CONTENT_REVIEW_SCREEN = 55705604;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "WP_MOBILE_ADMIN_REPORTED_CONTENT_REVIEW_SCREEN" : "WP_MOBILE_ADMIN_OPEN_REPORTED_CONTENT_LIST" : "WP_MOBILE_ADMIN_DEACTIVATE_USER_SINGLE" : "WP_MOBILE_ADMIN_ACTIVATE_USER_SINGLE";
    }
}
