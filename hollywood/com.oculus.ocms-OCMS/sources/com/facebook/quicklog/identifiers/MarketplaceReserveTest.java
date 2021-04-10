package com.facebook.quicklog.identifiers;

public class MarketplaceReserveTest {
    public static final int BUYER_RESERVATION_TEST = 634592018;
    public static final short MODULE_ID = 9683;

    public static String getMarkerName(int i) {
        return i != 6930 ? "UNDEFINED_QPL_EVENT" : "MARKETPLACE_RESERVE_TEST_BUYER_RESERVATION_TEST";
    }
}
