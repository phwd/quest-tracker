package com.facebook.quicklog.identifiers;

public class MinutiaeVerbPicker {
    public static final int FETCH_TIME = 4390916;
    public static final int FETCH_TIME_CACHED = 4390921;
    public static final short MODULE_ID = 67;
    public static final int TIME_TO_FETCH_END = 4390915;
    public static final int TIME_TO_FETCH_END_CACHED = 4390919;
    public static final int TIME_TO_FETCH_START = 4390914;
    public static final int TIME_TO_INIT = 4390913;
    public static final int TIME_TO_RESULTS_SHOWN = 4390923;
    public static final int TIME_TO_RESULTS_SHOWN_CACHED = 4390920;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 7 ? i != 8 ? i != 9 ? i != 11 ? "UNDEFINED_QPL_EVENT" : "MINUTIAE_VERB_PICKER_TIME_TO_RESULTS_SHOWN" : "MINUTIAE_VERB_PICKER_FETCH_TIME_CACHED" : "MINUTIAE_VERB_PICKER_TIME_TO_RESULTS_SHOWN_CACHED" : "MINUTIAE_VERB_PICKER_TIME_TO_FETCH_END_CACHED" : "MINUTIAE_VERB_PICKER_FETCH_TIME" : "MINUTIAE_VERB_PICKER_TIME_TO_FETCH_END" : "MINUTIAE_VERB_PICKER_TIME_TO_FETCH_START" : "MINUTIAE_VERB_PICKER_TIME_TO_INIT";
    }
}
