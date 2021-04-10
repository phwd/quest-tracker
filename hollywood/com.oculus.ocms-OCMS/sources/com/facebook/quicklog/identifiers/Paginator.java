package com.facebook.quicklog.identifiers;

public class Paginator {
    public static final short MODULE_ID = 107;
    public static final int NEXTCLICK = 7012353;
    public static final int PREVCLICK = 7012354;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "PAGINATOR_PREVCLICK" : "PAGINATOR_NEXTCLICK";
    }
}
