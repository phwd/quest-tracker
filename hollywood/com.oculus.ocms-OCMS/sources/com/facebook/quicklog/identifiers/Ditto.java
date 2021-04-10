package com.facebook.quicklog.identifiers;

public class Ditto {
    public static final int ACCOUNT_SWITCH = 20512770;
    public static final int HANDLE_LOGIN = 20512771;
    public static final short MODULE_ID = 313;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "DITTO_HANDLE_LOGIN" : "DITTO_ACCOUNT_SWITCH";
    }
}
