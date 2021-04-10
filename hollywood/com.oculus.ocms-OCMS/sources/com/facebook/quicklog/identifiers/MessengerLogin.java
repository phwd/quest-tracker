package com.facebook.quicklog.identifiers;

public class MessengerLogin {
    public static final int MESSENGER_INSTALL_REFERRER_FETCH = 22749031;
    public static final short MODULE_ID = 347;

    public static String getMarkerName(int i) {
        return i != 8039 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_LOGIN_MESSENGER_INSTALL_REFERRER_FETCH";
    }
}
