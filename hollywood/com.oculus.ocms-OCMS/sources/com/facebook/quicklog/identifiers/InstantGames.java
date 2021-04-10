package com.facebook.quicklog.identifiers;

public class InstantGames {
    public static final int INSTANT_GAME_LOAD = 57540609;
    public static final short MODULE_ID = 878;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "INSTANT_GAMES_INSTANT_GAME_LOAD";
    }
}
