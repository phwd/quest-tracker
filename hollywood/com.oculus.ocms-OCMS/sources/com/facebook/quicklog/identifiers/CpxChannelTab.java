package com.facebook.quicklog.identifiers;

public class CpxChannelTab {
    public static final int CPX_CHANNEL_TAB_LANDING = 49348609;
    public static final short MODULE_ID = 753;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CPX_CHANNEL_TAB_CPX_CHANNEL_TAB_LANDING";
    }
}
