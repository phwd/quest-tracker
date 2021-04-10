package com.facebook.quicklog.identifiers;

public class Hello {
    public static final int COLD_START = 5963781;
    public static final int DIALER_TAB = 5963780;
    public static final int EVERYONE_TAB = 5963777;
    public static final int LOCAL_SEARCH = 5963785;
    public static final int MAIN_ACTIVITY_CREATE = 5963783;
    public static final int ME_TAB = 5963778;
    public static final short MODULE_ID = 91;
    public static final int RECENT_TAB = 5963779;
    public static final int REMOTE_SEARCH = 5963787;
    public static final int REMOTE_SEARCH_NO_LOCATION = 5963789;
    public static final int SEARCH_TIME_TO_FIRST_RESULT = 5963786;
    public static final int SHOW_HISTORY = 5963784;
    public static final int WARM_START = 5963782;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "HELLO_EVERYONE_TAB";
            case 2:
                return "HELLO_ME_TAB";
            case 3:
                return "HELLO_RECENT_TAB";
            case 4:
                return "HELLO_DIALER_TAB";
            case 5:
                return "HELLO_COLD_START";
            case 6:
                return "HELLO_WARM_START";
            case 7:
                return "HELLO_MAIN_ACTIVITY_CREATE";
            case 8:
                return "HELLO_SHOW_HISTORY";
            case 9:
                return "HELLO_LOCAL_SEARCH";
            case 10:
                return "HELLO_SEARCH_TIME_TO_FIRST_RESULT";
            case 11:
                return "HELLO_REMOTE_SEARCH";
            case 12:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 13:
                return "HELLO_REMOTE_SEARCH_NO_LOCATION";
        }
    }
}
