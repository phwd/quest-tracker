package com.facebook.quicklog.identifiers;

public class MarketplaceRatingsReviews {
    public static final int C2C_RATINGS_REVIEWS_COMPOSER = 708983977;
    public static final int C2C_REVIEW_DISPLAY_PAGE = 708978470;
    public static final short MODULE_ID = 10818;

    public static String getMarkerName(int i) {
        return i != 10022 ? i != 15529 ? "UNDEFINED_QPL_EVENT" : "MARKETPLACE_RATINGS_REVIEWS_C2C_RATINGS_REVIEWS_COMPOSER" : "MARKETPLACE_RATINGS_REVIEWS_C2C_REVIEW_DISPLAY_PAGE";
    }
}
