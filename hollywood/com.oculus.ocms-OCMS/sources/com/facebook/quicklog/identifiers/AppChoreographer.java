package com.facebook.quicklog.identifiers;

public class AppChoreographer {
    public static final int IDLE_TASK_ANDROID = 32440321;
    public static final short MODULE_ID = 495;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "Idle tasks";
    }
}
