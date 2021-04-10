package com.facebook.quicklog.identifiers;

public class Spectrum {
    public static final int INITIALIZATION_ANDROID = 35192833;
    public static final short MODULE_ID = 537;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "SPECTRUM_INITIALIZATION_ANDROID";
    }
}
