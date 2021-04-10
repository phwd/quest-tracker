package com.facebook.quicklog.identifiers;

public class MilanChat {
    public static final int MILAN_CHAT_CLIENT_TTI = 64356353;
    public static final short MODULE_ID = 982;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MILAN_CHAT_MILAN_CHAT_CLIENT_TTI";
    }
}
