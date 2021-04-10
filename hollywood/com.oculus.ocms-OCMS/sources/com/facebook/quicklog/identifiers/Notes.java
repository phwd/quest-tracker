package com.facebook.quicklog.identifiers;

public class Notes {
    public static final short MODULE_ID = 131;
    public static final int NOTES_LOAD = 8585217;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "NOTES_NOTES_LOAD";
    }
}
