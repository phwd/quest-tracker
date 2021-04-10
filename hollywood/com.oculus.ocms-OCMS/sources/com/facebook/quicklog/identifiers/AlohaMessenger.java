package com.facebook.quicklog.identifiers;

public class AlohaMessenger {
    public static final int ALL_CONTACTS_MENU_OPEN_TIME = 969282628;
    public static final int INCOMING_CALL_LATENCY = 969287086;
    public static final short MODULE_ID = 14790;
    public static final int OUTGOING_CALL_LATENCY = 969293142;

    public static String getMarkerName(int i) {
        return i != 5188 ? i != 9646 ? i != 15702 ? "UNDEFINED_QPL_EVENT" : "ALOHA_MESSENGER_OUTGOING_CALL_LATENCY" : "ALOHA_MESSENGER_INCOMING_CALL_LATENCY" : "ALOHA_MESSENGER_ALL_CONTACTS_MENU_OPEN_TIME";
    }
}
