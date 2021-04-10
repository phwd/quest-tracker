package com.facebook.quicklog.identifiers;

public class WhatsappTtrc {
    public static final short MODULE_ID = 10741;
    public static final int WHATSAPP_TTRC_CHAT_OPEN = 703934026;
    public static final int WHATSAPP_TTRC_CHAT_OPEN_V2 = 703923896;
    public static final int WHATSAPP_TTRC_CREATE_APPLICATION = 703926783;

    public static String getMarkerName(int i) {
        return i != 1720 ? i != 4607 ? i != 11850 ? "UNDEFINED_QPL_EVENT" : "WHATSAPP_TTRC_WHATSAPP_TTRC_CHAT_OPEN" : "WHATSAPP_TTRC_WHATSAPP_TTRC_CREATE_APPLICATION" : "WHATSAPP_TTRC_WHATSAPP_TTRC_CHAT_OPEN_V2";
    }
}
