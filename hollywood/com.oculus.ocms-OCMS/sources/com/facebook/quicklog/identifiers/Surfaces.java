package com.facebook.quicklog.identifiers;

public class Surfaces {
    public static final int DATA_NAVIGATION_PARALLEL_FETCH = 25821186;
    public static final short MODULE_ID = 394;
    public static final int PAGINABLE_LIST_TAIL_LOAD = 25821189;
    public static final int PAGINATION_TAIL_LOAD = 25821188;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "SURFACES_PAGINABLE_LIST_TAIL_LOAD" : "SURFACES_PAGINATION_TAIL_LOAD" : "SURFACES_DATA_NAVIGATION_PARALLEL_FETCH";
    }
}
