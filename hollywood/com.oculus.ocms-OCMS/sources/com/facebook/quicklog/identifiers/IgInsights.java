package com.facebook.quicklog.identifiers;

public class IgInsights {
    public static final int IG_INSIGHTS_ACCOUNT_TTI = 39124996;
    public static final int IG_INSIGHTS_CLIPS_TTI = 39137013;
    public static final int IG_INSIGHTS_IGTV_TTI = 39130588;
    public static final int IG_INSIGHTS_POSTS_TTI = 39124994;
    public static final int IG_INSIGHTS_PRODUCTS_TTI = 39124995;
    public static final int IG_INSIGHTS_STORIES_TTI = 39124993;
    public static final short MODULE_ID = 597;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5596 ? i != 12021 ? "UNDEFINED_QPL_EVENT" : "IG_INSIGHTS_IG_INSIGHTS_CLIPS_TTI" : "IG_INSIGHTS_IG_INSIGHTS_IGTV_TTI" : "IG_INSIGHTS_IG_INSIGHTS_ACCOUNT_TTI" : "IG_INSIGHTS_IG_INSIGHTS_PRODUCTS_TTI" : "IG_INSIGHTS_IG_INSIGHTS_POSTS_TTI" : "IG_INSIGHTS_IG_INSIGHTS_STORIES_TTI";
    }
}
