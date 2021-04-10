package com.facebook.quicklog.identifiers;

public class Commerce {
    public static final int ANDROID_COMMERCE_VIEW_PRODUCT_DETAILS = 7077891;
    public static final int ANDROID_COMMERCE_VIEW_STOREFRONT = 7077890;
    public static final int ANDROID_COMMERCE_VIEW_STOREFRONT_COLLECTION = 7077889;
    public static final int B2C_TTI = 7077892;
    public static final short MODULE_ID = 108;
    public static final int REVIEW_COMPOSER_TTI = 7077893;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "COMMERCE_REVIEW_COMPOSER_TTI" : "COMMERCE_B2C_TTI" : "COMMERCE_ANDROID_COMMERCE_VIEW_PRODUCT_DETAILS" : "COMMERCE_ANDROID_COMMERCE_VIEW_STOREFRONT" : "COMMERCE_ANDROID_COMMERCE_VIEW_STOREFRONT_COLLECTION";
    }
}
