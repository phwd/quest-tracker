package com.facebook.quicklog.identifiers;

public class ReactNativeTtrc {
    public static final int CRISIS_ROUTE_TTRC = 51904514;
    public static final int MARKETPLACE_DELETE_DEDUPLICATED_LISTINGS_TTRC = 51914638;
    public static final int MARKETPLACE_LIVE_SHOPPING_TTRC = 51912311;
    public static final int MENU_BASKET_TTRC = 51920207;
    public static final int MENU_PDP_TTRC = 51911212;
    public static final int MENU_TTRC = 51915843;
    public static final short MODULE_ID = 792;
    public static final int OFF_FACEBOOK_ACTIVITY_REACT_NATIVE_TTRC = 51904519;
    public static final int PDP_AD_TTRC = 51904517;
    public static final int PRIVACY_SHORTCUTS_TTRC = 51904513;
    public static final int SEARCH_TYPEAHEAD_RESULTS_APP = 51912188;
    public static final int SHARED_RN_TTRC = 51904516;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 5 ? i != 7 ? i != 6700 ? i != 7676 ? i != 7799 ? i != 10126 ? i != 11331 ? i != 15695 ? "UNDEFINED_QPL_EVENT" : "REACT_NATIVE_TTRC_MENU_BASKET_TTRC" : "REACT_NATIVE_TTRC_MENU_TTRC" : "REACT_NATIVE_TTRC_MARKETPLACE_DELETE_DEDUPLICATED_LISTINGS_TTRC" : "REACT_NATIVE_TTRC_MARKETPLACE_LIVE_SHOPPING_TTRC" : "REACT_NATIVE_TTRC_SEARCH_TYPEAHEAD_RESULTS_APP" : "REACT_NATIVE_TTRC_MENU_PDP_TTRC" : "REACT_NATIVE_TTRC_OFF_FACEBOOK_ACTIVITY_REACT_NATIVE_TTRC" : "REACT_NATIVE_TTRC_PDP_AD_TTRC" : "REACT_NATIVE_TTRC_SHARED_RN_TTRC" : "REACT_NATIVE_TTRC_CRISIS_ROUTE_TTRC" : "REACT_NATIVE_TTRC_PRIVACY_SHORTCUTS_TTRC";
    }
}
