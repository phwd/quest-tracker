package com.facebook.quicklog.identifiers;

public class EntrypointsLoggingMessenger {
    public static final int LOGGING_ENTRYPOINTS = 18022401;
    public static final short MODULE_ID = 275;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ENTRYPOINTS_LOGGING_MESSENGER_LOGGING_ENTRYPOINTS";
    }
}
