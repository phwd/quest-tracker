package com.facebook.quicklog.identifiers;

public class FbAnalyticsApp {
    public static final int COLD_START_TTI = 36831235;
    public static final int DASHBOARD_LIST_LOAD = 36831237;
    public static final int ENTITIES_LIST_LOAD = 36831234;
    public static final int FILTER_BUILDER_LOAD = 36831240;
    public static final short MODULE_ID = 562;
    public static final int OVERVIEW_SCREEN_LOAD = 36831236;
    public static final int SELECT_ENTITY_TO_SHOW_OVERVIEW = 36831239;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 7 ? i != 8 ? "UNDEFINED_QPL_EVENT" : "FB_ANALYTICS_APP_FILTER_BUILDER_LOAD" : "FB_ANALYTICS_APP_SELECT_ENTITY_TO_SHOW_OVERVIEW" : "FB_ANALYTICS_APP_DASHBOARD_LIST_LOAD" : "FB_ANALYTICS_APP_OVERVIEW_SCREEN_LOAD" : "FB_ANALYTICS_APP_COLD_START_TTI" : "FB_ANALYTICS_APP_ENTITIES_LIST_LOAD";
    }
}
