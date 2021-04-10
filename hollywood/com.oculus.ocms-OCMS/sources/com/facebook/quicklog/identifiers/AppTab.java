package com.facebook.quicklog.identifiers;

public class AppTab {
    public static final int LOAD_TAB_TAB = 3932161;
    public static final int LOAD_TAB_TAB_NO_ANIM = 3932162;
    public static final short MODULE_ID = 60;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "APP_TAB_LOAD_TAB_TAB_NO_ANIM" : "APP_TAB_LOAD_TAB_TAB";
    }
}
