package com.facebook.quicklog.identifiers;

public class Today {
    public static final short MODULE_ID = 111;
    public static final int TODAY_NOTIFICATIONS_PULL_TO_REFRESH_TTI = 7274503;
    public static final int TODAY_NOTIFICATIONS_TTI = 7274501;
    public static final int TODAY_PULL_TO_REFRESH_TTI = 7274500;
    public static final int TODAY_TAB_OPEN_TTI = 7274499;
    public static final int TODAY_TTI = 7274497;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 7 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "TODAY_TODAY_NOTIFICATIONS_TTI" : "TODAY_TODAY_PULL_TO_REFRESH_TTI" : "TODAY_TODAY_TAB_OPEN_TTI" : "TODAY_TODAY_NOTIFICATIONS_PULL_TO_REFRESH_TTI" : "TODAY_TODAY_TTI";
    }
}
