package com.facebook.quicklog.identifiers;

public class MinutiaeObjectSelector {
    public static final int FETCH_TIME = 12976133;
    public static final int FETCH_TIME_CACHED = 12976136;
    public static final short MODULE_ID = 198;
    public static final int TIME_TO_FETCH_END = 12976131;
    public static final int TIME_TO_FETCH_END_CACHED = 12976134;
    public static final int TIME_TO_FETCH_START = 12976130;
    public static final int TIME_TO_INIT = 12976129;
    public static final int TIME_TO_RESULTS_SHOWN = 12976132;
    public static final int TIME_TO_RESULTS_SHOWN_CACHED = 12976135;
    public static final int TIME_TO_SCROLL_LOAD = 12976137;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "MINUTIAE_OBJECT_SELECTOR_TIME_TO_INIT";
            case 2:
                return "MINUTIAE_OBJECT_SELECTOR_TIME_TO_FETCH_START";
            case 3:
                return "MINUTIAE_OBJECT_SELECTOR_TIME_TO_FETCH_END";
            case 4:
                return "MINUTIAE_OBJECT_SELECTOR_TIME_TO_RESULTS_SHOWN";
            case 5:
                return "MINUTIAE_OBJECT_SELECTOR_FETCH_TIME";
            case 6:
                return "MINUTIAE_OBJECT_SELECTOR_TIME_TO_FETCH_END_CACHED";
            case 7:
                return "MINUTIAE_OBJECT_SELECTOR_TIME_TO_RESULTS_SHOWN_CACHED";
            case 8:
                return "MINUTIAE_OBJECT_SELECTOR_FETCH_TIME_CACHED";
            case 9:
                return "MINUTIAE_OBJECT_SELECTOR_TIME_TO_SCROLL_LOAD";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
