package com.facebook.quicklog.identifiers;

public class Engagement {
    public static final int AD_IMPRESSIONS = 7405572;
    public static final int COMMENTS = 7405570;
    public static final int LIKES = 7405571;
    public static final short MODULE_ID = 113;
    public static final int VPVS = 7405569;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "ENGAGEMENT_AD_IMPRESSIONS" : "ENGAGEMENT_LIKES" : "ENGAGEMENT_COMMENTS" : "ENGAGEMENT_VPVS";
    }
}
