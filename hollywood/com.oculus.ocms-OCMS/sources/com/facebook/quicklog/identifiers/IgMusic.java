package com.facebook.quicklog.identifiers;

public class IgMusic {
    public static final short MODULE_ID = 449;
    public static final int PLAY_START_TIME_ANDROID = 29425665;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_MUSIC_PLAY_START_TIME_ANDROID";
    }
}
