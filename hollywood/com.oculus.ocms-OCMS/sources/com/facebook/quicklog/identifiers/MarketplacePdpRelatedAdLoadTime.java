package com.facebook.quicklog.identifiers;

public class MarketplacePdpRelatedAdLoadTime {
    public static final int MARKETPLACE_PDP_RELATED_AD_LOADED = 54067201;
    public static final short MODULE_ID = 825;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MARKETPLACE_PDP_RELATED_AD_LOAD_TIME_MARKETPLACE_PDP_RELATED_AD_LOADED";
    }
}
