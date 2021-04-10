package com.facebook.quicklog.identifiers;

public class PagesInsights {
    public static final int AUDIENCE_LIST_TTI = 19202049;
    public static final int HOME_TTI = 19202052;
    public static final int MOBILE_HOME_TTRC = 19202074;
    public static final short MODULE_ID = 293;
    public static final int POST_LIST_TTI = 19202050;
    public static final int SINGLE_POST_TTI = 19202051;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 26 ? "UNDEFINED_QPL_EVENT" : "PAGES_INSIGHTS_MOBILE_HOME_TTRC" : "PAGES_INSIGHTS_HOME_TTI" : "PAGES_INSIGHTS_SINGLE_POST_TTI" : "PAGES_INSIGHTS_POST_LIST_TTI" : "PAGES_INSIGHTS_AUDIENCE_LIST_TTI";
    }
}
