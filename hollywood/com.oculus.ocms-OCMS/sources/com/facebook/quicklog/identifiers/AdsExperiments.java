package com.facebook.quicklog.identifiers;

public class AdsExperiments {
    public static final int ADS_EXPERIMENTS_TAL_OLD_WEB_FUNNEL = 183767196;
    public static final int ADS_EXPERIMENTS_WEB_FUNNEL = 183762945;
    public static final short MODULE_ID = 2804;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 4252 ? "UNDEFINED_QPL_EVENT" : "ADS_EXPERIMENTS_ADS_EXPERIMENTS_TAL_OLD_WEB_FUNNEL" : "ADS_EXPERIMENTS_ADS_EXPERIMENTS_WEB_FUNNEL";
    }
}
