package com.facebook.quicklog.identifiers;

public class GamingCoplay {
    public static final int COPLAY_FUNNEL = 640496395;
    public static final short MODULE_ID = 9773;

    public static String getMarkerName(int i) {
        return i != 13067 ? "UNDEFINED_QPL_EVENT" : "GAMING_COPLAY_COPLAY_FUNNEL";
    }
}
