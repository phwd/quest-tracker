package com.facebook.quicklog.identifiers;

public class Weather {
    public static final int DASHBOARD_TTI = 27590657;
    public static final short MODULE_ID = 421;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "WEATHER_PERMALINK_DASHBOARD_TTI";
    }
}
