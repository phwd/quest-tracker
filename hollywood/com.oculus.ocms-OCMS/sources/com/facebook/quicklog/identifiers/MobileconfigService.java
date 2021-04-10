package com.facebook.quicklog.identifiers;

public class MobileconfigService {
    public static final int GET_API = 645036435;
    public static final short MODULE_ID = 9842;
    public static final int SUBSCRIBE_API = 645021198;

    public static String getMarkerName(int i) {
        return i != 15886 ? i != 31123 ? "UNDEFINED_QPL_EVENT" : "MOBILECONFIG_SERVICE_GET_API" : "MOBILECONFIG_SERVICE_SUBSCRIBE_API";
    }
}
