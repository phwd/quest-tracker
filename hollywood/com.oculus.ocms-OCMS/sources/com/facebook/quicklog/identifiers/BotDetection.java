package com.facebook.quicklog.identifiers;

public class BotDetection {
    public static final int BOT_DETECTION_SIGNAL_COLLECTION_PERF = 29097985;
    public static final short MODULE_ID = 444;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "BOT_DETECTION_BOT_DETECTION_SIGNAL_COLLECTION_PERF";
    }
}
