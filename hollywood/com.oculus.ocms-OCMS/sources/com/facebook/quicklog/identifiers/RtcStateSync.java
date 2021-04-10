package com.facebook.quicklog.identifiers;

public class RtcStateSync {
    public static final short MODULE_ID = 886;
    public static final int UNSUBSCRIBE_REQUEST = 58064898;
    public static final int UPDATE_REQUEST = 58064897;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "RTC_STATE_SYNC_UNSUBSCRIBE_REQUEST" : "RTC_STATE_SYNC_UPDATE_REQUEST";
    }
}
