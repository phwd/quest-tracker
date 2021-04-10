package com.facebook.quicklog.identifiers;

public class BackgroundWork {
    public static final int GENERIC_BACKGROUND_WORK_FROM_LOGGER = 5767170;
    public static final int HANDLE_BROADCAST = 5767169;
    public static final short MODULE_ID = 88;
    public static final int SERVICE_ON_CREATE = 5767171;
    public static final int SERVICE_ON_DESTROY = 5767173;
    public static final int SERVICE_ON_START = 5767172;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "BACKGROUND_WORK_SERVICE_ON_DESTROY" : "BACKGROUND_WORK_SERVICE_ON_START" : "BACKGROUND_WORK_SERVICE_ON_CREATE" : "BACKGROUND_WORK_GENERIC_BACKGROUND_WORK_FROM_LOGGER" : "BACKGROUND_WORK_HANDLE_BROADCAST";
    }
}
