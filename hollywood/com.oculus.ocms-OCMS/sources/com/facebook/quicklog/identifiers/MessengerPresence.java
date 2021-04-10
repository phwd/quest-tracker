package com.facebook.quicklog.identifiers;

public class MessengerPresence {
    public static final int ACTIVE_STATUS_SETTING_ENTER = 91372485;
    public static final int INBOX_ACTIVE_NOW_LOADING = 91358261;
    public static final short MODULE_ID = 1394;

    public static String getMarkerName(int i) {
        return i != 1077 ? i != 15301 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_PRESENCE_ACTIVE_STATUS_SETTING_ENTER" : "MESSENGER_PRESENCE_INBOX_ACTIVE_NOW_LOADING";
    }
}
