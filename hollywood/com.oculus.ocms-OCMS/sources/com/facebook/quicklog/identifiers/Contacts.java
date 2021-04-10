package com.facebook.quicklog.identifiers;

public class Contacts {
    public static final int COLD_START = 4653057;
    public static final int DRAW_VIEW = 4653061;
    public static final int INFLATE_MAIN_ACTIVITY = 4653064;
    public static final int INJECT_MAIN_ACTIVITY = 4653065;
    public static final int LOCAL_SEARCH = 4653067;
    public static final int MAIN_ACTIVITY_CREATE = 4653059;
    public static final short MODULE_ID = 71;
    public static final int ON_CREATE_VIEW = 4653062;
    public static final int REMOTE_SEARCH = 4653068;
    public static final int SEARCH_TIME_TO_FIRST_RESULT = 4653066;
    public static final int SHOW_HISTORY = 4653060;
    public static final int TAB_CREATE = 4653063;
    public static final int WARM_START = 4653058;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "CONTACTS_COLD_START";
            case 2:
                return "CONTACTS_WARM_START";
            case 3:
                return "CONTACTS_MAIN_ACTIVITY_CREATE";
            case 4:
                return "CONTACTS_SHOW_HISTORY";
            case 5:
                return "CONTACTS_DRAW_VIEW";
            case 6:
                return "CONTACTS_ON_CREATE_VIEW";
            case 7:
                return "CONTACTS_TAB_CREATE";
            case 8:
                return "CONTACTS_INFLATE_MAIN_ACTIVITY";
            case 9:
                return "CONTACTS_INJECT_MAIN_ACTIVITY";
            case 10:
                return "CONTACTS_SEARCH_TIME_TO_FIRST_RESULT";
            case 11:
                return "CONTACTS_LOCAL_SEARCH";
            case 12:
                return "CONTACTS_REMOTE_SEARCH";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
