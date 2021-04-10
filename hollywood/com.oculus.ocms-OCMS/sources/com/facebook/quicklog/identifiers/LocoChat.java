package com.facebook.quicklog.identifiers;

public class LocoChat {
    public static final int LOCO_CHAT_INBOX_TTRC = 361367857;
    public static final short MODULE_ID = 5514;

    public static String getMarkerName(int i) {
        return i != 2353 ? "UNDEFINED_QPL_EVENT" : "LOCO_CHAT_LOCO_CHAT_INBOX_TTRC";
    }
}
