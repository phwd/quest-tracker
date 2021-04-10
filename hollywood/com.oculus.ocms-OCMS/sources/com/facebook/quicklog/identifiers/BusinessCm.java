package com.facebook.quicklog.identifiers;

public class BusinessCm {
    public static final int BIZAPP_POST_DETAIL_TTRC = 54984705;
    public static final int BIZAPP_POST_TAB_TTRC = 55009359;
    public static final short MODULE_ID = 839;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 24655 ? "UNDEFINED_QPL_EVENT" : "BUSINESS_CM_BIZAPP_POST_TAB_TTRC" : "BUSINESS_CM_BIZAPP_POST_DETAIL_TTRC";
    }
}
