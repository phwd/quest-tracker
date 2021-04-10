package com.facebook.quicklog.identifiers;

public class AndroidAccountSettings {
    public static final short MODULE_ID = 10000;
    public static final int OPEN_ACCOUNT_SETTINGS = 655386400;

    public static String getMarkerName(int i) {
        return i != 26400 ? "UNDEFINED_QPL_EVENT" : "ANDROID_ACCOUNT_SETTINGS_OPEN_ACCOUNT_SETTINGS";
    }
}
