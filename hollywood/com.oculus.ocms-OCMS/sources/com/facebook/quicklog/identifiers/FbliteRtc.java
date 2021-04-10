package com.facebook.quicklog.identifiers;

public class FbliteRtc {
    public static final short MODULE_ID = 910;
    public static final int RTC_ACTIVITY_CALL = 59637761;
    public static final int RTC_CALL_END = 59637763;
    public static final int RTC_CALL_RING = 59647728;
    public static final int RTC_CALL_START = 59637762;
    public static final int RTC_MSG_QUEUES_SIZE = 59647136;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 9376 ? i != 9968 ? "UNDEFINED_QPL_EVENT" : "FBLITE_RTC_RTC_CALL_RING" : "FBLITE_RTC_RTC_MSG_QUEUES_SIZE" : "FBLITE_RTC_RTC_CALL_END" : "FBLITE_RTC_RTC_CALL_START" : "FBLITE_RTC_RTC_ACTIVITY_CALL";
    }
}
