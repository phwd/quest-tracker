package com.facebook.quicklog.identifiers;

public class IgDownloadableModule {
    public static final short MODULE_ID = 475;
    public static final int WEBRTC = 31129601;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_DOWNLOADABLE_MODULE_WEBRTC";
    }
}
