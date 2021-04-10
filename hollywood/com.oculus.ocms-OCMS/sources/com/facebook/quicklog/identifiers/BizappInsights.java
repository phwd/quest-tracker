package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class BizappInsights {
    public static final int AUDIENCE_NAV_TO_FB = 448137762;
    public static final int AUDIENCE_NAV_TO_IG = 448137570;
    public static final int CHANGE_ACCOUNT = 448151300;
    public static final int CHANGE_TIMERANGE = 448137308;
    public static final int CONTENT_CHANGE_FILTER = 448138912;
    public static final int CONTENT_CHANGE_SORT = 448151194;
    public static final int CONTENT_LIST_LOAD_MORE = 448148786;
    public static final short MODULE_ID = 6838;
    public static final int NAV_TO_AUDIENCE_INSIGHTS_TAB = 448141029;
    public static final int NAV_TO_AUDIENCE_TAB = 448149939;
    public static final int NAV_TO_CONTENT_TAB = 448148861;
    public static final int NAV_TO_TRENDS_TAB = 448138018;
    public static final int PAGE_LOAD = 448137303;
    public static final int POTENTIAL_AUDIENCE_PAGE_LOAD = 448139186;
    public static final int TOOLTIP = 448141725;
    public static final int TRENDS_TAB_LOAD = 448141783;
    public static final int TREND_CHART_INTERACTION = 448148322;

    public static String getMarkerName(int i) {
        switch (i) {
            case UL.id._UL__ULSEP_android_app_Activity_ULSEP_BINDING_ID /*{ENCODED_INT: 2135}*/:
                return "BIZAPP_INSIGHTS_PAGE_LOAD";
            case 2140:
                return "BIZAPP_INSIGHTS_CHANGE_TIMERANGE";
            case 2402:
                return "BIZAPP_INSIGHTS_AUDIENCE_NAV_TO_IG";
            case 2594:
                return "BIZAPP_INSIGHTS_AUDIENCE_NAV_TO_FB";
            case 2850:
                return "BIZAPP_INSIGHTS_NAV_TO_TRENDS_TAB";
            case 3744:
                return "BIZAPP_INSIGHTS_CONTENT_CHANGE_FILTER";
            case 4018:
                return "BIZAPP_INSIGHTS_POTENTIAL_AUDIENCE_PAGE_LOAD";
            case 5861:
                return "BIZAPP_INSIGHTS_NAV_TO_AUDIENCE_INSIGHTS_TAB";
            case 6557:
                return "BIZAPP_INSIGHTS_TOOLTIP";
            case 6615:
                return "BIZAPP_INSIGHTS_TRENDS_TAB_LOAD";
            case 13154:
                return "BIZAPP_INSIGHTS_TREND_CHART_INTERACTION";
            case 13618:
                return "BIZAPP_INSIGHTS_CONTENT_LIST_LOAD_MORE";
            case 13693:
                return "BIZAPP_INSIGHTS_NAV_TO_CONTENT_TAB";
            case 14771:
                return "BIZAPP_INSIGHTS_NAV_TO_AUDIENCE_TAB";
            case 16026:
                return "BIZAPP_INSIGHTS_CONTENT_CHANGE_SORT";
            case 16132:
                return "BIZAPP_INSIGHTS_CHANGE_ACCOUNT";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
