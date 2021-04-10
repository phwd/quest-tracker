package com.facebook.quicklog.identifiers;

public class Prompts {
    public static final int DATA_FETCH = 9633793;
    public static final short MODULE_ID = 147;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "PROMPTS_DATA_FETCH";
    }
}
