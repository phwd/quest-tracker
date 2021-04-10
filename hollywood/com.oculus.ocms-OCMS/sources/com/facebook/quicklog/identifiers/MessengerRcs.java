package com.facebook.quicklog.identifiers;

public class MessengerRcs {
    public static final int IMPORT_SYSTEM_MESSAGES = 239934651;
    public static final short MODULE_ID = 3661;
    public static final int SEND_MESSAGE = 239943112;

    public static String getMarkerName(int i) {
        return i != 7355 ? i != 15816 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_RCS_SEND_MESSAGE" : "MESSENGER_RCS_IMPORT_SYSTEM_MESSAGES";
    }
}
