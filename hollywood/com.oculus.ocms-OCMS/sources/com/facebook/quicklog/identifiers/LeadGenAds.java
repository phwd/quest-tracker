package com.facebook.quicklog.identifiers;

public class LeadGenAds {
    public static final int LEAD_GEN_ADS_TTI = 14745601;
    public static final short MODULE_ID = 225;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "LEAD_GEN_ADS_LEAD_GEN_ADS_TTI";
    }
}
