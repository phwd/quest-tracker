package com.facebook.quicklog.identifiers;

public class OculusPanelapps {
    public static final int DEEP_VIEW_EXPANSION = 51718910;
    public static final int INIT = 51707905;
    public static final int LOAD_MORE_IN_SHELF = 51707909;
    public static final int LOAD_MORE_SHELVES = 51707908;
    public static final short MODULE_ID = 789;
    public static final int REACTVR_PROFILE = 51707912;
    public static final int SEARCH_BAR_QUERY = 51707910;
    public static final int SEARCH_INITIAL_LOAD = 51707911;
    public static final int SHARE_SHEET_INIT = 51707907;
    public static final int SUBFEED_SWITCH = 51721650;
    public static final int SWITCH_TAB = 51707906;
    public static final int TV_SEARCH_BAR_QUERY = 51723886;
    public static final int TV_SEARCH_BAR_TYPEAHEAD = 51718425;

    public static String getMarkerName(int i) {
        if (i == 10521) {
            return "OCULUS_PANELAPPS_TV_SEARCH_BAR_TYPEAHEAD";
        }
        if (i == 11006) {
            return "OCULUS_PANELAPPS_DEEP_VIEW_EXPANSION";
        }
        if (i == 13746) {
            return "OCULUS_PANELAPPS_SUBFEED_SWITCH";
        }
        if (i == 15982) {
            return "OCULUS_PANELAPPS_TV_SEARCH_BAR_QUERY";
        }
        switch (i) {
            case 1:
                return "OCULUS_PANELAPPS_INIT";
            case 2:
                return "OCULUS_PANELAPPS_SWITCH_TAB";
            case 3:
                return "OCULUS_PANELAPPS_SHARE_SHEET_INIT";
            case 4:
                return "OCULUS_PANELAPPS_LOAD_MORE_SHELVES";
            case 5:
                return "OCULUS_PANELAPPS_LOAD_MORE_IN_SHELF";
            case 6:
                return "OCULUS_PANELAPPS_SEARCH_BAR_QUERY";
            case 7:
                return "OCULUS_PANELAPPS_SEARCH_INITIAL_LOAD";
            case 8:
                return "OCULUS_PANELAPPS_REACTVR_PROFILE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
