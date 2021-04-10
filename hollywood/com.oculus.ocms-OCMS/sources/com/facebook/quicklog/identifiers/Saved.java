package com.facebook.quicklog.identifiers;

public class Saved {
    public static final short MODULE_ID = 24;
    public static final int PAGE_COLLECTION_TTI = 1572880;
    public static final int SAVED_DASHBOARD_START = 1572865;
    public static final int SAVE_COLLECTION_VIEW_TTRC = 1572886;
    public static final int SAVE_DASHBOARD_RECOMMENDATIONS_PIVOT_TTI = 1572879;
    public static final int SAVE_DASHBOARD_TTI = 1572868;
    public static final int SAVE_DASHBOARD_TTRC = 1572885;
    public static final int SAVE_DASHBOARD_UPSELL_INIT = 1572867;
    public static final int SAVE_DISCOVERY_COLLECTIONS_NAV_TTI = 1572877;
    public static final int SAVE_DISCOVERY_SAVE_PIVOTS_NAV_TTI = 1572876;
    public static final int SAVE_TRAVEL_DESTINATION_NAV_TTI = 1572882;
    public static final int SAVE_TRAVEL_EXPLORE_NAV_TTI = 1572881;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "SAVED_SAVED_DASHBOARD_START";
        }
        if (i == 3) {
            return "SAVED_SAVE_DASHBOARD_UPSELL_INIT";
        }
        if (i == 4) {
            return "Save Dashboard TTI (RN)";
        }
        if (i == 12) {
            return "SAVED_SAVE_DISCOVERY_SAVE_PIVOTS_NAV_TTI";
        }
        if (i == 13) {
            return "SAVED_SAVE_DISCOVERY_COLLECTIONS_NAV_TTI";
        }
        if (i == 21) {
            return "SAVED_SAVE_DASHBOARD_TTRC";
        }
        if (i == 22) {
            return "SAVED_SAVE_COLLECTION_VIEW_TTRC";
        }
        switch (i) {
            case 15:
                return "SAVED_SAVE_DASHBOARD_RECOMMENDATIONS_PIVOT_TTI";
            case 16:
                return "SAVED_PAGE_COLLECTION_TTI";
            case 17:
                return "SAVED_SAVE_TRAVEL_EXPLORE_NAV_TTI";
            case 18:
                return "SAVED_SAVE_TRAVEL_DESTINATION_NAV_TTI";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
