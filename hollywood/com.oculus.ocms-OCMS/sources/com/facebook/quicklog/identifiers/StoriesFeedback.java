package com.facebook.quicklog.identifiers;

public class StoriesFeedback {
    public static final int LWR_NOTIFICATION_OPEN = 23789576;
    public static final short MODULE_ID = 363;
    public static final int PRIVACY_SETTING_RELIABILITY = 23789575;
    public static final int REPLY_BAR_OPEN_PERFORMANCE = 23789572;
    public static final int REPLY_BAR_TTI = 23789570;
    public static final int VIEWER_SHEET_FETCH_TTI = 23789573;
    public static final int VIEWER_SHEET_PAGINATION_PERFORMANCE = 23789574;

    public static String getMarkerName(int i) {
        switch (i) {
            case 2:
                return "STORIES_FEEDBACK_REPLY_BAR_TTI";
            case 3:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 4:
                return "STORIES_FEEDBACK_REPLY_BAR_OPEN_PERFORMANCE";
            case 5:
                return "STORIES_FEEDBACK_VIEWER_SHEET_FETCH_TTI";
            case 6:
                return "STORIES_FEEDBACK_VIEWER_SHEET_PAGINATION_PERFORMANCE";
            case 7:
                return "STORIES_FEEDBACK_PRIVACY_SETTING_RELIABILITY";
            case 8:
                return "STORIES_FEEDBACK_LWR_NOTIFICATION_OPEN";
        }
    }
}
