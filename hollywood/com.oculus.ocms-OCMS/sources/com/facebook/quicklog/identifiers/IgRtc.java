package com.facebook.quicklog.identifiers;

public class IgRtc {
    public static final int CALL_SETUP = 29229058;
    public static final short MODULE_ID = 446;
    public static final int SIGNALING = 29229057;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "IG_RTC_CALL_SETUP" : "IG_RTC_SIGNALING";
    }
}
