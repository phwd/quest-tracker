package com.facebook.quicklog.identifiers;

public class PlayableAds {
    public static final short MODULE_ID = 1984;
    public static final int PLAYABLE_ADS_FUNNEL_TEST = 130030546;

    public static String getMarkerName(int i) {
        return i != 7122 ? "UNDEFINED_QPL_EVENT" : "PLAYABLE_ADS_PLAYABLE_ADS_FUNNEL_TEST";
    }
}
