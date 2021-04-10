package com.facebook.quicklog.identifiers;

public class QplInternal {
    public static final int ARIANE_TEST_PATTERNS = 36306947;
    public static final int FEED_SCROLL_ANDROID_AGGREGATED = 36306950;
    public static final int IG_ANDROID_SCROLL_AGGREGATED = 36322273;
    public static final int IMAGES_OUTLIERS_ANDROID_AGGREGATED = 36306954;
    public static final short MODULE_ID = 554;

    public static String getMarkerName(int i) {
        return i != 3 ? i != 6 ? i != 10 ? i != 15329 ? "UNDEFINED_QPL_EVENT" : "QPL_INTERNAL_IG_ANDROID_SCROLL_AGGREGATED" : "QPL_INTERNAL_IMAGES_OUTLIERS_ANDROID_AGGREGATED" : "QPL_INTERNAL_FEED_SCROLL_ANDROID_AGGREGATED" : "QPL_INTERNAL_ARIANE_TEST_PATTERNS";
    }
}
