package com.facebook.quicklog.identifiers;

public class WearableSpeechservice {
    public static final int APP_START = 959448428;
    public static final short MODULE_ID = 14640;

    public static String getMarkerName(int i) {
        return i != 1388 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_SPEECHSERVICE_APP_START";
    }
}
