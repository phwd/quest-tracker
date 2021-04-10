package com.facebook.quicklog.identifiers;

public class MessengerLocationSharing {
    public static final int MAP_LOAD = 681327365;
    public static final short MODULE_ID = 10396;

    public static String getMarkerName(int i) {
        return i != 15109 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_LOCATION_SHARING_MAP_LOAD";
    }
}
