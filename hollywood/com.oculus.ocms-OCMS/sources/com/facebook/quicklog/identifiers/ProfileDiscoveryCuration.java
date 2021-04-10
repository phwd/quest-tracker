package com.facebook.quicklog.identifiers;

public class ProfileDiscoveryCuration {
    public static final int DISCOVERY_DASHBOARD_TTI = 11730945;
    public static final int DISCOVERY_HOME_INITIAL_LOAD = 11730949;
    public static final int DISCOVERY_HOME_MORE_PAGE_WAIT_TIME = 11730953;
    public static final int DISCOVERY_HOME_TAIL_LOAD = 11730951;
    public static final int DISCOVERY_INTENT_SIGNALS_INITIAL_LOAD = 11730958;
    public static final int DISCOVERY_PROFILE_PREVIEW_INITIAL_LOAD = 11730957;
    public static final int DISCOVERY_SCOPED_VIEW_INITIAL_LOAD = 11730950;
    public static final int DISCOVERY_SCOPED_VIEW_MORE_PAGE_WAIT_TIME = 11730954;
    public static final int DISCOVERY_SCOPED_VIEW_TAIL_LOAD = 11730952;
    public static final short MODULE_ID = 179;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "PROFILE_DISCOVERY_CURATION_DISCOVERY_DASHBOARD_TTI";
        }
        if (i == 13) {
            return "PROFILE_DISCOVERY_CURATION_DISCOVERY_PROFILE_PREVIEW_INITIAL_LOAD";
        }
        if (i == 14) {
            return "PROFILE_DISCOVERY_CURATION_DISCOVERY_INTENT_SIGNALS_INITIAL_LOAD";
        }
        switch (i) {
            case 5:
                return "PROFILE_DISCOVERY_CURATION_DISCOVERY_HOME_INITIAL_LOAD";
            case 6:
                return "PROFILE_DISCOVERY_CURATION_DISCOVERY_SCOPED_VIEW_INITIAL_LOAD";
            case 7:
                return "PROFILE_DISCOVERY_CURATION_DISCOVERY_HOME_TAIL_LOAD";
            case 8:
                return "PROFILE_DISCOVERY_CURATION_DISCOVERY_SCOPED_VIEW_TAIL_LOAD";
            case 9:
                return "PROFILE_DISCOVERY_CURATION_DISCOVERY_HOME_MORE_PAGE_WAIT_TIME";
            case 10:
                return "PROFILE_DISCOVERY_CURATION_DISCOVERY_SCOPED_VIEW_MORE_PAGE_WAIT_TIME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
