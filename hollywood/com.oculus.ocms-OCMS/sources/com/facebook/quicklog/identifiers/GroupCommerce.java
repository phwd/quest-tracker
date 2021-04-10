package com.facebook.quicklog.identifiers;

public class GroupCommerce {
    public static final short MODULE_ID = 117;
    public static final int TODAY_NOTIFICATIONS_PULL_TO_REFRESH_TTI = 7667716;
    public static final int TODAY_NOTIFICATIONS_TAB_OPEN_TTI = 7667715;

    public static String getMarkerName(int i) {
        return i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "GROUP_COMMERCE_TODAY_NOTIFICATIONS_PULL_TO_REFRESH_TTI" : "GROUP_COMMERCE_TODAY_NOTIFICATIONS_TAB_OPEN_TTI";
    }
}
