package com.facebook.quicklog.identifiers;

public class IgAppStart {
    public static final int BACKGROUND_COLDSTART = 25100290;
    public static final int BACKGROUND_COLDSTART_ATTEMPT = 25100291;
    public static final int COLD_TO_FEED = 25100289;
    public static final int IG_APP_SERVICES = 25114593;
    public static final int IG_APP_SERVICES_EVENTS = 25111316;
    public static final short MODULE_ID = 383;
    public static final int SILENT_PUSH = 25100292;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 11028 ? i != 14305 ? "UNDEFINED_QPL_EVENT" : "IG_APP_START_IG_APP_SERVICES" : "IG_APP_START_IG_APP_SERVICES_EVENTS" : "IG_APP_START_SILENT_PUSH" : "IG_APP_START_BACKGROUND_COLDSTART_ATTEMPT" : "IG_APP_START_BACKGROUND_COLDSTART" : "IG_APP_START_COLD_TO_FEED";
    }
}
