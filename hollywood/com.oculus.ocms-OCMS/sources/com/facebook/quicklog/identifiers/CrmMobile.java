package com.facebook.quicklog.identifiers;

public class CrmMobile {
    public static final int COLD_START = 62259201;
    public static final short MODULE_ID = 950;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CRM_MOBILE_COLD_START";
    }
}
