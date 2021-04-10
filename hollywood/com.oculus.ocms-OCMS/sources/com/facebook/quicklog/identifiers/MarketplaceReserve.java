package com.facebook.quicklog.identifiers;

public class MarketplaceReserve {
    public static final int BUYER_RESERVE = 430374913;
    public static final int BUYER_RESERVE_SIMPLIFIED = 430390878;
    public static final short MODULE_ID = 6567;
    public static final int RESERVATION_LIFECYCLE = 430378067;
    public static final int SELLER_COMPOSER = 430391177;
    public static final int SELLER_COMPOSER_SIMPLIFIED = 430386496;
    public static final int SELLER_ONBOARDING = 430383451;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3155 ? i != 8539 ? i != 11584 ? i != 15966 ? i != 16265 ? "UNDEFINED_QPL_EVENT" : "MARKETPLACE_RESERVE_SELLER_COMPOSER" : "MARKETPLACE_RESERVE_BUYER_RESERVE_SIMPLIFIED" : "MARKETPLACE_RESERVE_SELLER_COMPOSER_SIMPLIFIED" : "MARKETPLACE_RESERVE_SELLER_ONBOARDING" : "MARKETPLACE_RESERVE_RESERVATION_LIFECYCLE" : "MARKETPLACE_RESERVE_BUYER_RESERVE";
    }
}
