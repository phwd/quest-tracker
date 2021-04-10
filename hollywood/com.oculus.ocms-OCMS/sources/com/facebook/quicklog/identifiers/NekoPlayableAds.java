package com.facebook.quicklog.identifiers;

public class NekoPlayableAds {
    public static final int CLOUD_LOAD = 60293121;
    public static final short MODULE_ID = 920;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "NEKO_PLAYABLE_ADS_CLOUD_LOAD";
    }
}
