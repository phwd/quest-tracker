package com.facebook.quicklog.identifiers;

public class IgAdsManager {
    public static final short MODULE_ID = 7146;
    public static final int PROMOTION_MANAGER_COLD_START_TIME = 468334897;

    public static String getMarkerName(int i) {
        return i != 14641 ? "UNDEFINED_QPL_EVENT" : "IG_ADS_MANAGER_PROMOTION_MANAGER_COLD_START_TIME";
    }
}
