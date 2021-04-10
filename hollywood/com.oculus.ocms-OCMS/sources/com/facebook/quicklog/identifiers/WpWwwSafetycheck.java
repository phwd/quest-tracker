package com.facebook.quicklog.identifiers;

public class WpWwwSafetycheck {
    public static final short MODULE_ID = 880;
    public static final int RESPOND_TO_SAFETY_CHECK_NT = 57683624;

    public static String getMarkerName(int i) {
        return i != 11944 ? "UNDEFINED_QPL_EVENT" : "WP_WWW_SAFETYCHECK_RESPOND_TO_SAFETY_CHECK_NT";
    }
}
