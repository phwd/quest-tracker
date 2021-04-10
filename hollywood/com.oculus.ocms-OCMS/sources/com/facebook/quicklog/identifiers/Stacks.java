package com.facebook.quicklog.identifiers;

public class Stacks {
    public static final int CAPTURE_PHOTO_TTI = 6160385;
    public static final short MODULE_ID = 94;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "STACKS_CAPTURE_PHOTO_TTI";
    }
}
