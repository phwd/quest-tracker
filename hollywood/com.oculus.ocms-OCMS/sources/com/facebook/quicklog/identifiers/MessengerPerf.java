package com.facebook.quicklog.identifiers;

public class MessengerPerf {
    public static final int INBOX_COLD_START_TTRC = 544415801;
    public static final short MODULE_ID = 8307;

    public static String getMarkerName(int i) {
        return i != 8249 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_PERF_INBOX_COLD_START_TTRC";
    }
}
