package com.facebook.quicklog.identifiers;

public class MobileJsDevReload {
    public static final short MODULE_ID = 236;
    public static final int RELOAD = 15466497;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MOBILE_JS_DEV_RELOAD_RELOAD";
    }
}
