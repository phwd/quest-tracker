package com.facebook.quicklog.identifiers;

public class MessengerDiscover {
    public static final int DISCOVER_TAB_FETCH_UNITS = 14221315;
    public static final int DISCOVER_TAB_RENDER = 14221314;
    public static final short MODULE_ID = 217;
    public static final int PLATFORM_DISCOVER_LOAD = 14221313;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_DISCOVER_DISCOVER_TAB_FETCH_UNITS" : "MESSENGER_DISCOVER_DISCOVER_TAB_RENDER" : "MESSENGER_DISCOVER_PLATFORM_DISCOVER_LOAD";
    }
}
