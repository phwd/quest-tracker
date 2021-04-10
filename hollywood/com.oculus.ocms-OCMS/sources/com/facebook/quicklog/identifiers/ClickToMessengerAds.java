package com.facebook.quicklog.identifiers;

public class ClickToMessengerAds {
    public static final int CTM_AD_SEND_WELCOME_MESSAGE = 619057579;
    public static final short MODULE_ID = 9446;
    public static final int ON_FEED_RENDER = 619065665;

    public static String getMarkerName(int i) {
        return i != 4523 ? i != 12609 ? "UNDEFINED_QPL_EVENT" : "CLICK_TO_MESSENGER_ADS_ON_FEED_RENDER" : "CLICK_TO_MESSENGER_ADS_CTM_AD_SEND_WELCOME_MESSAGE";
    }
}
