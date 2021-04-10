package com.facebook.quicklog.identifiers;

public class WpWhiteboard {
    public static final short MODULE_ID = 3252;
    public static final int NOTIFICATION_SHOWN = 213130080;
    public static final int SESSION_RECEIVED = 213129059;
    public static final int UI_CLOSED = 213131472;
    public static final int UI_OPENED = 213135039;

    public static String getMarkerName(int i) {
        return i != 5987 ? i != 7008 ? i != 8400 ? i != 11967 ? "UNDEFINED_QPL_EVENT" : "WP_WHITEBOARD_UI_OPENED" : "WP_WHITEBOARD_UI_CLOSED" : "WP_WHITEBOARD_NOTIFICATION_SHOWN" : "WP_WHITEBOARD_SESSION_RECEIVED";
    }
}
