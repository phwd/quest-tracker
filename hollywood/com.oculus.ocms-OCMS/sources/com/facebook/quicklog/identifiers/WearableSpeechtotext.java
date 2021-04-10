package com.facebook.quicklog.identifiers;

public class WearableSpeechtotext {
    public static final int APP_START = 518208352;
    public static final short MODULE_ID = 7907;

    public static String getMarkerName(int i) {
        return i != 15200 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_SPEECHTOTEXT_APP_START";
    }
}
