package com.facebook.quicklog.identifiers;

public class InstantGamesArcade {
    public static final int INITIAL_QUERY_FETCH = 50462721;
    public static final short MODULE_ID = 770;
    public static final int TTRC = 50462722;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "INSTANT_GAMES_ARCADE_TTRC" : "INSTANT_GAMES_ARCADE_INITIAL_QUERY_FETCH";
    }
}
