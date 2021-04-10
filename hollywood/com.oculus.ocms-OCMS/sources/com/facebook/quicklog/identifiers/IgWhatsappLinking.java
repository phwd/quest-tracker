package com.facebook.quicklog.identifiers;

public class IgWhatsappLinking {
    public static final int FUNNEL_EVENT_NAME = 402398226;
    public static final short MODULE_ID = 6140;

    public static String getMarkerName(int i) {
        return i != 7186 ? "UNDEFINED_QPL_EVENT" : "IG_WHATSAPP_LINKING_FUNNEL_EVENT_NAME";
    }
}
