package com.facebook.quicklog.identifiers;

public class LoginOneTap {
    public static final short MODULE_ID = 7364;
    public static final int ONE_TAP_CREDENTIAL_RETRIEVAL = 482609765;
    public static final int ONE_TAP_CREDENTIAL_USAGE = 482621765;

    public static String getMarkerName(int i) {
        return i != 2661 ? i != 14661 ? "UNDEFINED_QPL_EVENT" : "LOGIN_ONE_TAP_ONE_TAP_CREDENTIAL_USAGE" : "LOGIN_ONE_TAP_ONE_TAP_CREDENTIAL_RETRIEVAL";
    }
}
