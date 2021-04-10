package com.facebook.quicklog.identifiers;

public class WearableCalendar {
    public static final int APP_START = 136652613;
    public static final short MODULE_ID = 2085;

    public static String getMarkerName(int i) {
        return i != 10053 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_CALENDAR_APP_START";
    }
}
