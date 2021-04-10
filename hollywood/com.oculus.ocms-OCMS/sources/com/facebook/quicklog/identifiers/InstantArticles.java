package com.facebook.quicklog.identifiers;

public class InstantArticles {
    public static final int INSTANT_ARTICLE_LOAD = 6029313;
    public static final short MODULE_ID = 92;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "INSTANT_ARTICLES_INSTANT_ARTICLE_LOAD";
    }
}
