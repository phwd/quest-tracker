package com.facebook.quicklog.identifiers;

public class MessengerContentTriggers {
    public static final int CONTENT_SEARCH_INITIAL_RESULT_FETCH = 6815746;
    public static final int CONTENT_SEARCH_MEDIA_FETCH = 6815745;
    public static final short MODULE_ID = 104;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_CONTENT_TRIGGERS_CONTENT_SEARCH_INITIAL_RESULT_FETCH" : "MESSENGER_CONTENT_TRIGGERS_CONTENT_SEARCH_MEDIA_FETCH";
    }
}
