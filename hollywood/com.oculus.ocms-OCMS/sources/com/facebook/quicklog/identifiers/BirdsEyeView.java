package com.facebook.quicklog.identifiers;

public class BirdsEyeView {
    public static final int MEMORY = 56557569;
    public static final short MODULE_ID = 863;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "BIRDS_EYE_VIEW_MEMORY";
    }
}
