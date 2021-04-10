package com.facebook.quicklog.identifiers;

public class Minutiae {
    public static final int ICON_PICKER_FETCH_TIME = 4259845;
    public static final int ICON_PICKER_FETCH_TIME_CACHED = 4259850;
    public static final int ICON_PICKER_RENDERING_TIME = 4259846;
    public static final int ICON_PICKER_TIME_TO_FETCH_END = 4259843;
    public static final int ICON_PICKER_TIME_TO_FETCH_END_CACHED = 4259848;
    public static final int ICON_PICKER_TIME_TO_FETCH_START = 4259842;
    public static final int ICON_PICKER_TIME_TO_INIT = 4259841;
    public static final int ICON_PICKER_TIME_TO_RESULTS_SHOWN = 4259844;
    public static final int ICON_PICKER_TIME_TO_RESULTS_SHOWN_CACHED = 4259849;
    public static final int ICON_PICKER_TIME_TO_SEARCH_RESULT_SHOWN = 4259847;
    public static final short MODULE_ID = 65;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "MINUTIAE_ICON_PICKER_TIME_TO_INIT";
            case 2:
                return "MINUTIAE_ICON_PICKER_TIME_TO_FETCH_START";
            case 3:
                return "MINUTIAE_ICON_PICKER_TIME_TO_FETCH_END";
            case 4:
                return "MINUTIAE_ICON_PICKER_TIME_TO_RESULTS_SHOWN";
            case 5:
                return "MINUTIAE_ICON_PICKER_FETCH_TIME";
            case 6:
                return "MINUTIAE_ICON_PICKER_RENDERING_TIME";
            case 7:
                return "MINUTIAE_ICON_PICKER_TIME_TO_SEARCH_RESULT_SHOWN";
            case 8:
                return "MINUTIAE_ICON_PICKER_TIME_TO_FETCH_END_CACHED";
            case 9:
                return "MINUTIAE_ICON_PICKER_TIME_TO_RESULTS_SHOWN_CACHED";
            case 10:
                return "MINUTIAE_ICON_PICKER_FETCH_TIME_CACHED";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
