package com.facebook.quicklog.identifiers;

public class PortalWorkplaceLive {
    public static final int HOME_SCREEN_LOAD = 970726044;
    public static final short MODULE_ID = 14812;

    public static String getMarkerName(int i) {
        return i != 6812 ? "UNDEFINED_QPL_EVENT" : "PORTAL_WORKPLACE_LIVE_HOME_SCREEN_LOAD";
    }
}
