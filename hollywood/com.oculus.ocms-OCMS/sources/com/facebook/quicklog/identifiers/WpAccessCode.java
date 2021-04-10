package com.facebook.quicklog.identifiers;

public class WpAccessCode {
    public static final int GENERATION_SHARING = 58392577;
    public static final short MODULE_ID = 891;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "WP_ACCESS_CODE_GENERATION_SHARING";
    }
}
