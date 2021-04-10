package com.facebook.quicklog.identifiers;

public class MessengerInboxAds {
    public static final int AD_LOADING_ANDROID = 14155779;
    public static final int AD_RENDER_COLD_START_ANDROID = 14155780;
    public static final short MODULE_ID = 216;

    public static String getMarkerName(int i) {
        return i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_INBOX_ADS_AD_RENDER_COLD_START_ANDROID" : "MESSENGER_INBOX_ADS_AD_LOADING_ANDROID";
    }
}
