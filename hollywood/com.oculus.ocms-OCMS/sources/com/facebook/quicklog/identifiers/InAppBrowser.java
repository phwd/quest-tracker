package com.facebook.quicklog.identifiers;

public class InAppBrowser {
    public static final int LINK_CLICK = 19791876;
    public static final short MODULE_ID = 302;

    public static String getMarkerName(int i) {
        return i != 4 ? "UNDEFINED_QPL_EVENT" : "IN_APP_BROWSER_LINK_CLICK";
    }
}
