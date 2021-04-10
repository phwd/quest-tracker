package com.facebook.quicklog.identifiers;

public class Settings {
    public static final int LANDING_PAGE_TTI = 35258370;
    public static final int LANDING_PAGE_TTRC = 35258369;
    public static final short MODULE_ID = 538;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "SETTINGS_LANDING_PAGE_TTI" : "SETTINGS_LANDING_PAGE_TTRC";
    }
}
