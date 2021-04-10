package com.facebook.quicklog.identifiers;

public class ForegroundService {
    public static final short MODULE_ID = 423;
    public static final int START_FOREGROUND_SERVICE = 27721729;
    public static final int WEBRTC_IN_CALL_NOTIFICATION_SERVICE = 27721730;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "FOREGROUND_SERVICE_WEBRTC_IN_CALL_NOTIFICATION_SERVICE" : "FOREGROUND_SERVICE_START_FOREGROUND_SERVICE";
    }
}
