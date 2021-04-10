package com.facebook.quicklog.identifiers;

public class MinutiaeFeelingSelector {
    public static final int FETCH_TIME = 12910597;
    public static final int FETCH_TIME_CACHED = 12910602;
    public static final short MODULE_ID = 197;
    public static final int TIME_TO_FETCH_END = 12910595;
    public static final int TIME_TO_FETCH_END_CACHED = 12910600;
    public static final int TIME_TO_FETCH_START = 12910594;
    public static final int TIME_TO_INIT = 12910593;
    public static final int TIME_TO_RESULTS_SHOWN = 12910596;
    public static final int TIME_TO_RESULTS_SHOWN_CACHED = 12910601;
    public static final int TIME_TO_SCROLL_LOAD = 12910603;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "MINUTIAE_FEELING_SELECTOR_TIME_TO_INIT";
            case 2:
                return "MINUTIAE_FEELING_SELECTOR_TIME_TO_FETCH_START";
            case 3:
                return "MINUTIAE_FEELING_SELECTOR_TIME_TO_FETCH_END";
            case 4:
                return "MINUTIAE_FEELING_SELECTOR_TIME_TO_RESULTS_SHOWN";
            case 5:
                return "MINUTIAE_FEELING_SELECTOR_FETCH_TIME";
            case 6:
            case 7:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 8:
                return "MINUTIAE_FEELING_SELECTOR_TIME_TO_FETCH_END_CACHED";
            case 9:
                return "MINUTIAE_FEELING_SELECTOR_TIME_TO_RESULTS_SHOWN_CACHED";
            case 10:
                return "MINUTIAE_FEELING_SELECTOR_FETCH_TIME_CACHED";
            case 11:
                return "MINUTIAE_FEELING_SELECTOR_TIME_TO_SCROLL_LOAD";
        }
    }
}
