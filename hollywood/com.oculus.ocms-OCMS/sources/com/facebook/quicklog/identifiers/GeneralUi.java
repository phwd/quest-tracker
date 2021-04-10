package com.facebook.quicklog.identifiers;

public class GeneralUi {
    public static final int COLD_START = 3670019;
    public static final int MAIN_ACTIVITY_CREATE = 3670020;
    public static final int MAIN_ACTIVITY_INTENT_TO_FRAGMENT_CREATE = 3670024;
    public static final short MODULE_ID = 56;
    public static final int PLATFORM_DIALOG_ACTIVITY = 3670023;
    public static final int TAB_CREATE = 3670022;
    public static final int WARM_START = 3670021;

    public static String getMarkerName(int i) {
        switch (i) {
            case 3:
                return "GENERAL_UI_COLD_START";
            case 4:
                return "GENERAL_UI_MAIN_ACTIVITY_CREATE";
            case 5:
                return "GENERAL_UI_WARM_START";
            case 6:
                return "GENERAL_UI_TAB_CREATE";
            case 7:
                return "GENERAL_UI_PLATFORM_DIALOG_ACTIVITY";
            case 8:
                return "GENERAL_UI_MAIN_ACTIVITY_INTENT_TO_FRAGMENT_CREATE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
