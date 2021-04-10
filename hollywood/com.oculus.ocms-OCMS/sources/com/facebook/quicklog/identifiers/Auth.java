package com.facebook.quicklog.identifiers;

public class Auth {
    public static final int APP_ONCREATE = 5111812;
    public static final int HANDLE_DITTO_LOGOUT = 5111813;
    public static final short MODULE_ID = 78;
    public static final int SIGNAL_AUTH_COMPONENTS_ON_AUTH_COMPLETE = 5111811;

    public static String getMarkerName(int i) {
        return i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "AUTH_HANDLE_DITTO_LOGOUT" : "AUTH_APP_ONCREATE" : "SignalAuthComponentsOnAuthComplete";
    }
}
