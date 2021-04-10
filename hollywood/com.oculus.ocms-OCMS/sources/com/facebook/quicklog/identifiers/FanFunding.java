package com.facebook.quicklog.identifiers;

public class FanFunding {
    public static final int CONSIDERATION_PAGE_TTI = 26673153;
    public static final short MODULE_ID = 407;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FAN_FUNDING_CONSIDERATION_PAGE_TTI";
    }
}
