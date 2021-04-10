package com.facebook.quicklog.identifiers;

public class FbShopTab {
    public static final int HOME_FEED_TTRC = 1012729892;
    public static final short MODULE_ID = 15453;
    public static final int SHOPPING_PREFERENCES_LANDING_TTRC = 1012729365;

    public static String getMarkerName(int i) {
        return i != 1557 ? i != 2084 ? "UNDEFINED_QPL_EVENT" : "FB_SHOP_TAB_HOME_FEED_TTRC" : "FB_SHOP_TAB_SHOPPING_PREFERENCES_LANDING_TTRC";
    }
}
