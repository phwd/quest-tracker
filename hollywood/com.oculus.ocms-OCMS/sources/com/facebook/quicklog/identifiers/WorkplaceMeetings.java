package com.facebook.quicklog.identifiers;

public class WorkplaceMeetings {
    public static final short MODULE_ID = 5364;
    public static final int RSVP = 351543111;

    public static String getMarkerName(int i) {
        return i != 8007 ? "UNDEFINED_QPL_EVENT" : "WORKPLACE_MEETINGS_RSVP";
    }
}
