package com.facebook.quicklog.identifiers;

public class InstantExperienceAds {
    public static final int DOCUMENT_GEN_ELEMENTS_TIME = 29047276;
    public static final int DOCUMENT_LOAD_TIME = 29032449;
    public static final short MODULE_ID = 443;
    public static final int OPEN_IX_DOCUMENT_TTRC = 29032450;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 14828 ? "UNDEFINED_QPL_EVENT" : "INSTANT_EXPERIENCE_ADS_DOCUMENT_GEN_ELEMENTS_TIME" : "INSTANT_EXPERIENCE_ADS_OPEN_IX_DOCUMENT_TTRC" : "INSTANT_EXPERIENCE_ADS_DOCUMENT_LOAD_TIME";
    }
}
