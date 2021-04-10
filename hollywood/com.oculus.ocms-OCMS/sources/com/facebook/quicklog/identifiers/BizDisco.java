package com.facebook.quicklog.identifiers;

public class BizDisco {
    public static final int BIZ_DISCO_DATA_FETCH = 701960033;
    public static final int BIZ_DISCO_FEED_TTRC = 701967606;
    public static final int BIZ_DISCO_PAGINATION_TTI = 701959767;
    public static final short MODULE_ID = 10711;

    public static String getMarkerName(int i) {
        return i != 3671 ? i != 3937 ? i != 11510 ? "UNDEFINED_QPL_EVENT" : "BIZ_DISCO_BIZ_DISCO_FEED_TTRC" : "BIZ_DISCO_BIZ_DISCO_DATA_FETCH" : "BIZ_DISCO_BIZ_DISCO_PAGINATION_TTI";
    }
}
