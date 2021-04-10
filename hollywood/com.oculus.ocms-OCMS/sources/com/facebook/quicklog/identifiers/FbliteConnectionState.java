package com.facebook.quicklog.identifiers;

public class FbliteConnectionState {
    public static final int FIZZ_MODULE = 19988481;
    public static final short MODULE_ID = 305;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FBLITE_CONNECTION_STATE_FIZZ_MODULE";
    }
}
