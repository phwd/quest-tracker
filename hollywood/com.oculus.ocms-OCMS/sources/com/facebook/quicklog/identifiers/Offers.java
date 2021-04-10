package com.facebook.quicklog.identifiers;

public class Offers {
    public static final int LOADPERMALINK = 9109506;
    public static final int LOADWALLET = 9109505;
    public static final short MODULE_ID = 139;
    public static final int OFFER_ADS_DETAILS_TTRC = 9109508;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "OFFERS_OFFER_ADS_DETAILS_TTRC" : "OFFERS_LOADPERMALINK" : "OFFERS_LOADWALLET";
    }
}
