package com.facebook.quicklog.identifiers;

public class BackgroundLocationSettings {
    public static final int FETCH_DATA = 2490370;
    public static final int INIT = 2490371;
    public static final short MODULE_ID = 38;
    public static final int OVERALL_TTI = 2490369;
    public static final int RENDER = 2490372;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "BACKGROUND_LOCATION_SETTINGS_RENDER" : "BACKGROUND_LOCATION_SETTINGS_INIT" : "BACKGROUND_LOCATION_SETTINGS_FETCH_DATA" : "BACKGROUND_LOCATION_SETTINGS_OVERALL_TTI";
    }
}
