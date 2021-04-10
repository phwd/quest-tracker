package com.facebook.quicklog.identifiers;

public class AlohaCalendar {
    public static final int LOAD_CALENDAR = 990975376;
    public static final int LOAD_REMINDER = 990981693;
    public static final short MODULE_ID = 15121;

    public static String getMarkerName(int i) {
        return i != 5520 ? i != 11837 ? "UNDEFINED_QPL_EVENT" : "ALOHA_CALENDAR_LOAD_REMINDER" : "ALOHA_CALENDAR_LOAD_CALENDAR";
    }
}
