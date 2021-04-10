package com.facebook.quicklog.identifiers;

public class PagesPlatform {
    public static final short MODULE_ID = 154;
    public static final int TIME_TO_BOOTSTRAP = 10092545;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "PAGES_PLATFORM_TIME_TO_BOOTSTRAP";
    }
}
