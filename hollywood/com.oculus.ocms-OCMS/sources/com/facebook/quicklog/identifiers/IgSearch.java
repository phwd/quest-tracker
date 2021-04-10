package com.facebook.quicklog.identifiers;

public class IgSearch {
    public static final int IGTV_SEARCH_QUERY_FETCH = 32309251;
    public static final int IG_SEARCH_QUERY_FETCH = 32309250;
    public static final int IG_SEARCH_RESULTS_CLICKED_FUNNEL = 32312156;
    public static final short MODULE_ID = 493;
    public static final int TYPEAHEAD_TOUCH_DOWN = 32325527;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 2908 ? i != 16279 ? "UNDEFINED_QPL_EVENT" : "IG_SEARCH_TYPEAHEAD_TOUCH_DOWN" : "IG_SEARCH_IG_SEARCH_RESULTS_CLICKED_FUNNEL" : "IG_SEARCH_IGTV_SEARCH_QUERY_FETCH" : "IG_SEARCH_IG_SEARCH_QUERY_FETCH";
    }
}
