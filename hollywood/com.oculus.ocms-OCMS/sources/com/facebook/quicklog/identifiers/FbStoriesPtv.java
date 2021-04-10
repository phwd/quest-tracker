package com.facebook.quicklog.identifiers;

public class FbStoriesPtv {
    public static final short MODULE_ID = 819;
    public static final int PTV_TTI = 53673985;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FB_STORIES_PTV_PTV_TTI";
    }
}
