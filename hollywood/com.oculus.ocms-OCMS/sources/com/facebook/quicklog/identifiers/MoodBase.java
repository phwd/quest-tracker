package com.facebook.quicklog.identifiers;

public class MoodBase {
    public static final short MODULE_ID = 858;
    public static final int MOOD_BASE_ENTER = 56229889;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MOOD_BASE_MOOD_BASE_ENTER";
    }
}
