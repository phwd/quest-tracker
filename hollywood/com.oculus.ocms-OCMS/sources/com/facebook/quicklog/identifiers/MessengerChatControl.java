package com.facebook.quicklog.identifiers;

public class MessengerChatControl {
    public static final int BLOCK_PARTICIPANT = 57147396;
    public static final int DELETE_MESSAGE = 57147393;
    public static final int DELETE_PARTICIPANT = 57147394;
    public static final int HIDE_PERMANENTLY = 57147395;
    public static final short MODULE_ID = 872;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_CHAT_CONTROL_BLOCK_PARTICIPANT" : "MESSENGER_CHAT_CONTROL_HIDE_PERMANENTLY" : "MESSENGER_CHAT_CONTROL_DELETE_PARTICIPANT" : "MESSENGER_CHAT_CONTROL_DELETE_MESSAGE";
    }
}
