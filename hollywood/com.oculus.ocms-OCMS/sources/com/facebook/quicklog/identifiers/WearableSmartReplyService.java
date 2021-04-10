package com.facebook.quicklog.identifiers;

public class WearableSmartReplyService {
    public static final int APP_START = 328080194;
    public static final short MODULE_ID = 5006;
    public static final int SMART_REPLY_LATENCY = 328083926;

    public static String getMarkerName(int i) {
        return i != 6978 ? i != 10710 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_SMART_REPLY_SERVICE_SMART_REPLY_LATENCY" : "WEARABLE_SMART_REPLY_SERVICE_APP_START";
    }
}
