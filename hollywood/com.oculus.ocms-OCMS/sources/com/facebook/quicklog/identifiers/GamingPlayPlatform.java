package com.facebook.quicklog.identifiers;

public class GamingPlayPlatform {
    public static final int GAMES_QUICKSILVER_FUNNEL = 273481729;
    public static final short MODULE_ID = 4173;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "GAMING_PLAY_PLATFORM_GAMES_QUICKSILVER_FUNNEL";
    }
}
