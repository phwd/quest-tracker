package com.facebook.quicklog.identifiers;

public class AlohaAppstore {
    public static final int APP_INSTALL_TEST = 673195596;
    public static final short MODULE_ID = 10272;

    public static String getMarkerName(int i) {
        return i != 9804 ? "UNDEFINED_QPL_EVENT" : "ALOHA_APPSTORE_APP_INSTALL_TEST";
    }
}
