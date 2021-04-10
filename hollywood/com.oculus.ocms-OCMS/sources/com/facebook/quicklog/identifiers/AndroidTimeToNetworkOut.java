package com.facebook.quicklog.identifiers;

public class AndroidTimeToNetworkOut {
    public static final int ANDROID_TIME_TO_NETWORK_OUT = 24051713;
    public static final short MODULE_ID = 367;
    public static final int SMOLLA_EVENT1_ANDROID = 24051714;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "ANDROID_TIME_TO_NETWORK_OUT_SMOLLA_EVENT1_ANDROID" : "ANDROID_TIME_TO_NETWORK_OUT_ANDROID_TIME_TO_NETWORK_OUT";
    }
}
