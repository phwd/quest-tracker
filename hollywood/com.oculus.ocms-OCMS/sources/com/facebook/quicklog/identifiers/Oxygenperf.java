package com.facebook.quicklog.identifiers;

public class Oxygenperf {
    public static final short MODULE_ID = 96;
    public static final int STUBLAUNCH = 6291457;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "OXYGENPERF_STUBLAUNCH";
    }
}
