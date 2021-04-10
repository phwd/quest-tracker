package com.facebook.quicklog.identifiers;

public class Livewith {
    public static final int LIVE_SWAP_TO_RTC = 13893634;
    public static final short MODULE_ID = 212;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "LIVEWITH_LIVE_SWAP_TO_RTC";
    }
}
