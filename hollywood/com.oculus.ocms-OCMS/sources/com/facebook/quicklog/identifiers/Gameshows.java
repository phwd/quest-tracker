package com.facebook.quicklog.identifiers;

public class Gameshows {
    public static final short MODULE_ID = 496;
    public static final int QUESTION_SHOW_ANDROID = 32505857;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "GAMESHOWS_QUESTION_SHOW_ANDROID";
    }
}
