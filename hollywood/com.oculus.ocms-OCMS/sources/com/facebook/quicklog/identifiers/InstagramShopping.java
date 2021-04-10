package com.facebook.quicklog.identifiers;

public class InstagramShopping {
    public static final int CHECKOUT_TTI = 37355529;
    public static final int CONTINUE_SHOPPING_TTI = 37370080;
    public static final int FUCHSIA_LOAD = 37355528;
    public static final int FUCHSIA_TTI = 37355527;
    public static final int INDEX_CART_TTI = 37361281;
    public static final int MERCHANT_CART_TTI = 37362470;
    public static final short MODULE_ID = 570;
    public static final int PDP_2ND_PAGE_RENDER_TIME = 37364992;
    public static final int PDP_LOAD = 37355522;
    public static final int PDP_TTI = 37355521;
    public static final int PLACE_ORDER_TTI = 37355531;
    public static final int PRODUCT_COLLECTION_LOAD = 37355530;
    public static final int PROFILE_SHOP_LOAD = 37355526;
    public static final int PROFILE_SHOP_TTI = 37355525;
    public static final int SHOPPING_ACTIVITY_FEED_LOAD = 37362491;
    public static final int SHOPPING_ACTIVITY_FEED_TTI = 37379956;
    public static final int SHOPPING_ONBOARDING_BLOKS_ENTRY_PERF = 37383707;
    public static final int SHOP_HOME_LOAD = 37355524;
    public static final int SHOP_HOME_TTI = 37355523;
    public static final int TAG_PRODUCT = 37371463;
    public static final int TAG_PRODUCT_TTI = 37369682;
    public static final int WISHLIST_TTI = 37357157;

    public static String getMarkerName(int i) {
        if (i == 1637) {
            return "INSTAGRAM_SHOPPING_WISHLIST_TTI";
        }
        if (i == 5761) {
            return "INSTAGRAM_SHOPPING_INDEX_CART_TTI";
        }
        if (i == 6950) {
            return "INSTAGRAM_SHOPPING_MERCHANT_CART_TTI";
        }
        if (i == 6971) {
            return "INSTAGRAM_SHOPPING_SHOPPING_ACTIVITY_FEED_LOAD";
        }
        if (i == 9472) {
            return "INSTAGRAM_SHOPPING_PDP_2ND_PAGE_RENDER_TIME";
        }
        if (i == 14162) {
            return "INSTAGRAM_SHOPPING_TAG_PRODUCT_TTI";
        }
        if (i == 14560) {
            return "INSTAGRAM_SHOPPING_CONTINUE_SHOPPING_TTI";
        }
        if (i == 15943) {
            return "INSTAGRAM_SHOPPING_TAG_PRODUCT";
        }
        if (i == 24436) {
            return "INSTAGRAM_SHOPPING_SHOPPING_ACTIVITY_FEED_TTI";
        }
        if (i == 28187) {
            return "INSTAGRAM_SHOPPING_SHOPPING_ONBOARDING_BLOKS_ENTRY_PERF";
        }
        switch (i) {
            case 1:
                return "INSTAGRAM_SHOPPING_PDP_TTI";
            case 2:
                return "INSTAGRAM_SHOPPING_PDP_LOAD";
            case 3:
                return "INSTAGRAM_SHOPPING_SHOP_HOME_TTI";
            case 4:
                return "INSTAGRAM_SHOPPING_SHOP_HOME_LOAD";
            case 5:
                return "INSTAGRAM_SHOPPING_PROFILE_SHOP_TTI";
            case 6:
                return "INSTAGRAM_SHOPPING_PROFILE_SHOP_LOAD";
            case 7:
                return "INSTAGRAM_SHOPPING_FUCHSIA_TTI";
            case 8:
                return "INSTAGRAM_SHOPPING_FUCHSIA_LOAD";
            case 9:
                return "INSTAGRAM_SHOPPING_CHECKOUT_TTI";
            case 10:
                return "INSTAGRAM_SHOPPING_PRODUCT_COLLECTION_LOAD";
            case 11:
                return "INSTAGRAM_SHOPPING_PLACE_ORDER_TTI";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
