package com.facebook.quicklog.identifiers;

public class Reactions {
    public static final int LOAD_INPUT_DOCK_COLD = 8519684;
    public static final int LOAD_INPUT_DOCK_WARM = 8519683;
    public static final int LOAD_REACTORS_LIST = 8519685;
    public static final int LOAD_REACTORS_LIST_ON_SCROLL = 8519689;
    public static final int LOAD_REACTORS_LIST_TAB = 8519688;
    public static final short MODULE_ID = 130;
    public static final int REACT_TO_COMMENT = 8519693;
    public static final int REACT_TO_POST = 8519692;
    public static final int WAIT_TIME_REACTORS_LIST_CURRENT_TAB = 8519686;

    public static String getMarkerName(int i) {
        switch (i) {
            case 3:
                return "REACTIONS_LOAD_INPUT_DOCK_WARM";
            case 4:
                return "REACTIONS_LOAD_INPUT_DOCK_COLD";
            case 5:
                return "REACTIONS_LOAD_REACTORS_LIST";
            case 6:
                return "REACTIONS_WAIT_TIME_REACTORS_LIST_CURRENT_TAB";
            case 7:
            case 10:
            case 11:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 8:
                return "REACTIONS_LOAD_REACTORS_LIST_TAB";
            case 9:
                return "REACTIONS_LOAD_REACTORS_LIST_ON_SCROLL";
            case 12:
                return "REACTIONS_REACT_TO_POST";
            case 13:
                return "REACTIONS_REACT_TO_COMMENT";
        }
    }
}
