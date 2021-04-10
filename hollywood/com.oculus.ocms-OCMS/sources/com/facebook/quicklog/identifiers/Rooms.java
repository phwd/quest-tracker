package com.facebook.quicklog.identifiers;

public class Rooms {
    public static final int FB4A_RTC_RTC_ACTIVITY_CALL = 57810661;
    public static final int FB4A_RTC_RTC_CALL_END = 57818876;
    public static final int FB4A_RTC_RTC_CALL_START = 57810281;
    public static final int FB_TOFU_CREATE_ROOM = 57802753;
    public static final short MODULE_ID = 882;
    public static final int ROOMS_TRAY_START_UP = 57818457;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 7529 ? i != 7909 ? i != 15705 ? i != 16124 ? "UNDEFINED_QPL_EVENT" : "ROOMS_FB4A_RTC_RTC_CALL_END" : "ROOMS_ROOMS_TRAY_START_UP" : "ROOMS_FB4A_RTC_RTC_ACTIVITY_CALL" : "ROOMS_FB4A_RTC_RTC_CALL_START" : "ROOMS_FB_TOFU_CREATE_ROOM";
    }
}
