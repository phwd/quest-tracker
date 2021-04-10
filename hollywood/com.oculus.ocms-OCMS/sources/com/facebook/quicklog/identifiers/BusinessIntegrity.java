package com.facebook.quicklog.identifiers;

public class BusinessIntegrity {
    public static final int AD_ACTIVITY_FB4A = 14614533;
    public static final short MODULE_ID = 223;

    public static String getMarkerName(int i) {
        return i != 5 ? "UNDEFINED_QPL_EVENT" : "BUSINESS_INTEGRITY_AD_ACTIVITY_FB4A";
    }
}
