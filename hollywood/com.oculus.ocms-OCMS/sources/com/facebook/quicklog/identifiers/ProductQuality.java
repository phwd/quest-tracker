package com.facebook.quicklog.identifiers;

public class ProductQuality {
    public static final short MODULE_ID = 334;
    public static final int SURFACE_NAV_ANDROID = 21889027;

    public static String getMarkerName(int i) {
        return i != 3 ? "UNDEFINED_QPL_EVENT" : "PRODUCT_QUALITY_SURFACE_NAV_ANDROID";
    }
}
