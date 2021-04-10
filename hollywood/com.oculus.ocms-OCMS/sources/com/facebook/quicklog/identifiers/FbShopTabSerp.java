package com.facebook.quicklog.identifiers;

public class FbShopTabSerp {
    public static final short MODULE_ID = 12930;
    public static final int SERP_FEED_TTRC = 847391246;

    public static String getMarkerName(int i) {
        return i != 10766 ? "UNDEFINED_QPL_EVENT" : "FB_SHOP_TAB_SERP_SERP_FEED_TTRC";
    }
}
