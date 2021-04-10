package com.facebook.quicklog.identifiers;

public class MessengerSecondarySearch {
    public static final short MODULE_ID = 10455;
    public static final int SECONDARY_SEARCH = 685187852;

    public static String getMarkerName(int i) {
        return i != 8972 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_SECONDARY_SEARCH_SECONDARY_SEARCH";
    }
}
