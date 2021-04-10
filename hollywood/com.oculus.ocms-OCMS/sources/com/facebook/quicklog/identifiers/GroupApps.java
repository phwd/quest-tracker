package com.facebook.quicklog.identifiers;

public class GroupApps {
    public static final int GROUPS_APP_FUNNEL = 1010107390;
    public static final short MODULE_ID = 15413;

    public static String getMarkerName(int i) {
        return i != 1022 ? "UNDEFINED_QPL_EVENT" : "GROUP_APPS_GROUPS_APP_FUNNEL";
    }
}
