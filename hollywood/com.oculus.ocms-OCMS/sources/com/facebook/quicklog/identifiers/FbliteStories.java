package com.facebook.quicklog.identifiers;

public class FbliteStories {
    public static final short MODULE_ID = 504;
    public static final int TRAY_LOAD_TTI_CLIENT_PTR = 33030147;
    public static final int TRAY_LOAD_TTI_SERVER = 33030146;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "FBLITE_STORIES_TRAY_LOAD_TTI_CLIENT_PTR" : "FBLITE_STORIES_TRAY_LOAD_TTI_SERVER";
    }
}
