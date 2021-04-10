package com.facebook.quicklog.identifiers;

public class MinutiaeObjectPicker {
    public static final int FETCH_TIME = 4325381;
    public static final int FETCH_TIME_CACHED = 4325386;
    public static final short MODULE_ID = 66;
    public static final int TIME_TO_FETCH_END = 4325379;
    public static final int TIME_TO_FETCH_END_CACHED = 4325384;
    public static final int TIME_TO_FETCH_START = 4325378;
    public static final int TIME_TO_INIT = 4325377;
    public static final int TIME_TO_RESULTS_SHOWN = 4325380;
    public static final int TIME_TO_RESULTS_SHOWN_CACHED = 4325385;
    public static final int TIME_TO_SCROLL_LOAD = 4325387;
    public static final int TIME_TO_SEARCH_SHOWN = 4325383;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "MINUTIAE_OBJECT_PICKER_TIME_TO_INIT";
            case 2:
                return "MINUTIAE_OBJECT_PICKER_TIME_TO_FETCH_START";
            case 3:
                return "MINUTIAE_OBJECT_PICKER_TIME_TO_FETCH_END";
            case 4:
                return "MINUTIAE_OBJECT_PICKER_TIME_TO_RESULTS_SHOWN";
            case 5:
                return "MINUTIAE_OBJECT_PICKER_FETCH_TIME";
            case 6:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 7:
                return "MINUTIAE_OBJECT_PICKER_TIME_TO_SEARCH_SHOWN";
            case 8:
                return "MINUTIAE_OBJECT_PICKER_TIME_TO_FETCH_END_CACHED";
            case 9:
                return "MINUTIAE_OBJECT_PICKER_TIME_TO_RESULTS_SHOWN_CACHED";
            case 10:
                return "MINUTIAE_OBJECT_PICKER_FETCH_TIME_CACHED";
            case 11:
                return "MINUTIAE_OBJECT_PICKER_TIME_TO_SCROLL_LOAD";
        }
    }
}
