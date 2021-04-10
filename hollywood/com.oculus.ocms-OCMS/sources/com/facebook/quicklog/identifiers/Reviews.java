package com.facebook.quicklog.identifiers;

public class Reviews {
    public static final short MODULE_ID = 23;
    public static final int REVIEWS_LIST_LOAD_SECTIONS = 1507329;
    public static final int REVIEWS_LIST_SEE_MORE = 1507330;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "REVIEWS_REVIEWS_LIST_SEE_MORE" : "REVIEWS_REVIEWS_LIST_LOAD_SECTIONS";
    }
}
