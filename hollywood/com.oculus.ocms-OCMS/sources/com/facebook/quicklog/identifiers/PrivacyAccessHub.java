package com.facebook.quicklog.identifiers;

public class PrivacyAccessHub {
    public static final int ACCESS_HUB_UI_ACTIONS = 348132017;
    public static final short MODULE_ID = 5312;

    public static String getMarkerName(int i) {
        return i != 4785 ? "UNDEFINED_QPL_EVENT" : "PRIVACY_ACCESS_HUB_ACCESS_HUB_UI_ACTIONS";
    }
}
