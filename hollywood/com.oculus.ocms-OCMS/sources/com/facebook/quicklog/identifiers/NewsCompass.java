package com.facebook.quicklog.identifiers;

public class NewsCompass {
    public static final int COMPASS_FETCH = 35520513;
    public static final int COMPASS_PAGING_TTRC = 35520515;
    public static final int COMPASS_TTRC = 35520514;
    public static final short MODULE_ID = 542;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "NEWS_COMPASS_COMPASS_PAGING_TTRC" : "NEWS_COMPASS_COMPASS_TTRC" : "NEWS_COMPASS_COMPASS_FETCH";
    }
}
