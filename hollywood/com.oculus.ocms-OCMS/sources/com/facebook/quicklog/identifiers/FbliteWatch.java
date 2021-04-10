package com.facebook.quicklog.identifiers;

public class FbliteWatch {
    public static final int FBLITE_WATCH_CHANNELS = 52494337;
    public static final short MODULE_ID = 801;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "Fblite Channels Incoming Stories";
    }
}
