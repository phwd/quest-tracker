package com.facebook.quicklog.identifiers;

public class WpFrontlineWorkingHours {
    public static final short MODULE_ID = 887;
    public static final int WP_FRONTLINE_WORKING_HOURS_ALERT = 58130433;
    public static final int WP_FRONTLINE_WORKING_HOURS_ALERT_QUERY = 58130434;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "WP_FRONTLINE_WORKING_HOURS_WP_FRONTLINE_WORKING_HOURS_ALERT_QUERY" : "WP_FRONTLINE_WORKING_HOURS_WP_FRONTLINE_WORKING_HOURS_ALERT";
    }
}
