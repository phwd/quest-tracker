package com.facebook.quicklog.identifiers;

public class MessengerMessageSearch {
    public static final int MESSAGE_SEARCH = 804270274;
    public static final short MODULE_ID = 12272;

    public static String getMarkerName(int i) {
        return i != 12482 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_MESSAGE_SEARCH_MESSAGE_SEARCH";
    }
}
