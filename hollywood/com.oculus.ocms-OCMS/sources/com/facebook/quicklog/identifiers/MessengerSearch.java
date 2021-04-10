package com.facebook.quicklog.identifiers;

public class MessengerSearch {
    public static final short MODULE_ID = 479;
    public static final int NULL_STATE_RENDER = 31391745;
    public static final int NULL_STATE_RENDER_ANDROID = 31391746;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_SEARCH_NULL_STATE_RENDER_ANDROID" : "MESSENGER_SEARCH_NULL_STATE_RENDER";
    }
}
