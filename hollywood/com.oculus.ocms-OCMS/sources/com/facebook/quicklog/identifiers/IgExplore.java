package com.facebook.quicklog.identifiers;

public class IgExplore {
    public static final int IG_EXPLORE_PAGINATION_REQUEST = 16449540;
    public static final int IG_EXPLORE_PTR_LOAD = 16449538;
    public static final int IG_EXPLORE_TAB_LOAD = 16449537;
    public static final int IG_EXPLORE_TAIL_LOAD = 16449539;
    public static final short MODULE_ID = 251;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "IG_EXPLORE_IG_EXPLORE_PAGINATION_REQUEST" : "IG_EXPLORE_IG_EXPLORE_TAIL_LOAD" : "IG_EXPLORE_IG_EXPLORE_PTR_LOAD" : "IG_EXPLORE_IG_EXPLORE_TAB_LOAD";
    }
}
