package com.facebook.quicklog.identifiers;

public class IgNavigation {
    public static final int IG_NAVIGATION_ANIMATION = 34406401;
    public static final short MODULE_ID = 525;
    public static final int NAVIGATION_EVENT = 34420479;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 14079 ? "UNDEFINED_QPL_EVENT" : "IG_NAVIGATION_NAVIGATION_EVENT" : "IG_NAVIGATION_IG_NAVIGATION_ANIMATION";
    }
}
