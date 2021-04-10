package com.facebook.quicklog.identifiers;

public class MarketplaceLocalRatingsReview {
    public static final int BACK_PRESS = 1067520151;
    public static final int CANCEL_PRESS = 1067529253;
    public static final int COMPLETED_RATING = 1067517419;
    public static final int LOADIND_SELLER_RATING_PAGE = 1067517733;
    public static final int LOADING_BUYER_RATING_PAGE = 1067527719;
    public static final int LOADING_SELLER_RATING_PAGE = 1067522643;
    public static final short MODULE_ID = 16289;
    public static final int RATING_SELECT = 1067531221;
    public static final int RATING_TEXT_FOCUS = 1067528181;
    public static final int SKIP_PRESS = 1067527791;
    public static final int SUBMIT_FAIL = 1067526665;
    public static final int SUBMIT_INVALID = 1067517555;
    public static final int SUBMIT_PRESS = 1067518860;
    public static final int SUBMIT_SUCCESS = 1067525140;
    public static final int TAGS_UPDATE = 1067524004;
    public static final int TXN_SURVEY = 1067517009;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1105:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_TXN_SURVEY";
            case 1515:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_COMPLETED_RATING";
            case 1651:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_SUBMIT_INVALID";
            case 1829:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_LOADIND_SELLER_RATING_PAGE";
            case 2956:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_SUBMIT_PRESS";
            case 4247:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_BACK_PRESS";
            case 6739:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_LOADING_SELLER_RATING_PAGE";
            case 8100:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_TAGS_UPDATE";
            case 9236:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_SUBMIT_SUCCESS";
            case 10761:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_SUBMIT_FAIL";
            case 11815:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_LOADING_BUYER_RATING_PAGE";
            case 11887:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_SKIP_PRESS";
            case 12277:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_RATING_TEXT_FOCUS";
            case 13349:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_CANCEL_PRESS";
            case 15317:
                return "MARKETPLACE_LOCAL_RATINGS_REVIEW_RATING_SELECT";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
