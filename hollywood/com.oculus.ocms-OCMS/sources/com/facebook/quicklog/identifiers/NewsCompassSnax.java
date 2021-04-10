package com.facebook.quicklog.identifiers;

public class NewsCompassSnax {
    public static final short MODULE_ID = 13337;
    public static final int SNAX_BOTTOM_SHEET_BOTTOM_LOAD = 874083575;
    public static final int SNAX_BOTTOM_SHEET_TOP_LOAD = 874076815;

    public static String getMarkerName(int i) {
        return i != 23183 ? i != 29943 ? "UNDEFINED_QPL_EVENT" : "NEWS_COMPASS_SNAX_SNAX_BOTTOM_SHEET_BOTTOM_LOAD" : "NEWS_COMPASS_SNAX_SNAX_BOTTOM_SHEET_TOP_LOAD";
    }
}
