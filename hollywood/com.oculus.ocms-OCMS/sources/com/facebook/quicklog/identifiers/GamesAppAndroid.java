package com.facebook.quicklog.identifiers;

public class GamesAppAndroid {
    public static final int COLD_START = 26279943;
    public static final int COLD_START_DEX_LOAD = 26279939;
    public static final int COLD_START_LOGIN = 26279938;
    public static final int COLD_START_MAIN = 26279937;
    public static final int FEED_LOAD_TIME = 26279941;
    public static final int GAMES_APP_FEED_TTI = 26279942;
    public static final int INITIAL_FEED_LOAD_TIME = 26279940;
    public static final short MODULE_ID = 401;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "GAMES_APP_ANDROID_COLD_START_MAIN";
            case 2:
                return "GAMES_APP_ANDROID_COLD_START_LOGIN";
            case 3:
                return "GAMES_APP_ANDROID_COLD_START_DEX_LOAD";
            case 4:
                return "GAMES_APP_ANDROID_INITIAL_FEED_LOAD_TIME";
            case 5:
                return "GAMES_APP_ANDROID_FEED_LOAD_TIME";
            case 6:
                return "GAMES_APP_ANDROID_GAMES_APP_FEED_TTI";
            case 7:
                return "GAMES_APP_ANDROID_COLD_START";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
