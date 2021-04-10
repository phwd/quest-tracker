package com.facebook.quicklog.identifiers;

public class Friending {
    public static final int DYNAMIC_FRIENDING_TAB_TTI_FB4A = 3080218;
    public static final int DYNAMIC_FRIENDING_TAB_TTRC_FB4A = 3080219;
    public static final int FRIENDS_TAB_DISK_LOAD_METRIC_NAME = 3080204;
    public static final int FRIENDS_TAB_NETWORK_LOAD_METRIC_NAME = 3080205;
    public static final int FRIENDS_TAB_TAIL_LOAD = 3080224;
    public static final int FRIENDS_TAB_TTI_METRIC_NAME = 3080200;
    public static final int FRIENDS_TAB_WITH_ACTIVE_TTI_METRIC_NAME = 3080207;
    public static final int FRIEND_REQUESTS_HARRISON_TAB_SWITCH_TTI = 3080198;
    public static final int LOCATION = 3080193;
    public static final short MODULE_ID = 47;
    public static final int NUX_ADD_FRIENDS_STEP_TTI = 3080199;
    public static final int REQUESTS_TAB_TTI_METRIC_NAME = 3080201;
    public static final int SEARCH = 3080194;
    public static final int SEARCH_TAB_TTI_METRIC_NAME = 3080202;
    public static final int SUGGESTIONS_TAB_SCROLLING_METRIC_FB4A = 3080208;
    public static final int SUGGESTIONS_TAB_TTI_METRIC_NAME = 3080203;
    public static final int TTI_FRIENDING_TAB_FB4A = 3080222;
    public static final int TTRC_JEWEL_FB4A = 3080214;
    public static final int TTRC_NUX_FB4A = 3080215;
    public static final int TTRC_PLUS_IMAGES_JEWEL_FB4A = 3080225;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "FRIENDING_LOCATION";
        }
        if (i == 2) {
            return "FRIENDING_SEARCH";
        }
        if (i == 15) {
            return "FRIENDING_FRIENDS_TAB_WITH_ACTIVE_TTI_METRIC_NAME";
        }
        if (i == 16) {
            return "FRIENDING_SUGGESTIONS_TAB_SCROLLING_METRIC_FB4A";
        }
        if (i == 22) {
            return "FRIENDING_TTRC_JEWEL_FB4A";
        }
        if (i == 23) {
            return "FRIENDING_TTRC_NUX_FB4A";
        }
        if (i == 26) {
            return "FRIENDING_DYNAMIC_FRIENDING_TAB_TTI_FB4A";
        }
        if (i == 27) {
            return "FRIENDING_DYNAMIC_FRIENDING_TAB_TTRC_FB4A";
        }
        if (i == 30) {
            return "FRIENDING_TTI_FRIENDING_TAB_FB4A";
        }
        if (i == 32) {
            return "FRIENDING_FRIENDS_TAB_TAIL_LOAD";
        }
        if (i == 33) {
            return "FRIENDING_TTRC_PLUS_IMAGES_JEWEL_FB4A";
        }
        switch (i) {
            case 6:
                return "FRIENDING_FRIEND_REQUESTS_HARRISON_TAB_SWITCH_TTI";
            case 7:
                return "FRIENDING_NUX_ADD_FRIENDS_STEP_TTI";
            case 8:
                return "FRIENDING_FRIENDS_TAB_TTI_METRIC_NAME";
            case 9:
                return "FRIENDING_REQUESTS_TAB_TTI_METRIC_NAME";
            case 10:
                return "FRIENDING_SEARCH_TAB_TTI_METRIC_NAME";
            case 11:
                return "FRIENDING_SUGGESTIONS_TAB_TTI_METRIC_NAME";
            case 12:
                return "FRIENDING_FRIENDS_TAB_DISK_LOAD_METRIC_NAME";
            case 13:
                return "FRIENDING_FRIENDS_TAB_NETWORK_LOAD_METRIC_NAME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
