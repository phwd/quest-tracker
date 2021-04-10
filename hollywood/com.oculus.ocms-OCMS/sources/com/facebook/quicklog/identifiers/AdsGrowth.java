package com.facebook.quicklog.identifiers;

public class AdsGrowth {
    public static final short MODULE_ID = 791;
    public static final int RN_PAGE_SELECTOR_TTI = 51838977;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ADS_GROWTH_RN_PAGE_SELECTOR_TTI";
    }
}
