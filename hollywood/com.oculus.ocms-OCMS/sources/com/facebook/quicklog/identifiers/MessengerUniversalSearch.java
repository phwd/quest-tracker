package com.facebook.quicklog.identifiers;

public class MessengerUniversalSearch {
    public static final short MODULE_ID = 4305;
    public static final int START = 282146427;
    public static final int UNIVERSAL_SEARCH = 282147745;

    public static String getMarkerName(int i) {
        return i != 13947 ? i != 15265 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_UNIVERSAL_SEARCH_UNIVERSAL_SEARCH" : "MESSENGER_UNIVERSAL_SEARCH_START";
    }
}
