package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class Marketplace {
    public static final int ALL_PAYOUTS_TTI = 11075664;
    public static final int ALL_TRANSACTIONS_TTI = 11075663;
    public static final int AUTOS_TTI = 11075612;
    public static final int B2C_PRODUCT_DETAILS = 11075605;
    public static final int BANK_ACCOUNT_EDIT_TTI = 11075659;
    public static final int BUYING_TTI = 11075623;
    public static final int BUY_SELL_GROUP_HOME_TTI = 11075640;
    public static final int CATEGORY_FEED_TTI = 11075673;
    public static final int CATEGORY_FEED_TTRC = 11099253;
    public static final int CATEGORY_LISTING_VIEW_PAGINATION_TTI = 11075675;
    public static final int CATEGORY_SURFACE_RESULT_TTRC = 11075658;
    public static final int COMMERCE_PROFILE_TTRC = 11075678;
    public static final int COMPOSER_EDIT_TTI = 11075609;
    public static final int COMPOSER_POST_TTI = 11075604;
    public static final int COMPOSER_TTI = 11075618;
    public static final int DAILY_DEALS_TTI = 11075599;
    public static final int DAILY_DEALS_TTRC = 11075665;
    public static final int DEBUG_SETTINGS_TTI = 11075647;
    public static final int ECOMM_HOME_TTRC = 11075682;
    public static final int ECOMM_UNIFIED_LANDING_TTI = 11075662;
    public static final int ECOMM_UNIFIED_LANDING_TTRC = 11075661;
    public static final int EDIT_COMPOSER_TTI = 11075619;
    public static final int FEED_AFTER_TTI_FIRST_PAGINATION_DELAY = 11075602;
    public static final int FEED_IMAGE_LOAD = 11075592;
    public static final int FEED_ITEM_PDP_MEASURE = 11075616;
    public static final int FEED_LOAD = 11075590;
    public static final int FILTERED_CATEGORY_SEARCH_TTI = 11075641;
    public static final int FILTERS_TTI = 11075670;
    public static final int FOLLOWING_INFORMATION_TTI = 11075621;
    public static final int FOLLOWING_TTI = 11075624;
    public static final int HOISTED_PDP_LOAD_TTRC = 11075672;
    public static final int HOMEFEED_FIRST_SCROLL = 11079025;
    public static final int HOME_LOAD = 11075598;
    public static final int HOME_PAGINATION_TTI = 11075651;
    public static final int HOME_SERVICES_TTI = 11075642;
    public static final int HOME_TTI = 11075593;
    public static final int HOME_TTRC = 11075648;
    public static final int INBOX_TTI = 11075625;
    public static final int INDIA_HOME_LOAD = 11075610;
    public static final int INITIAL_MESSAGING_TTI = 11075636;
    public static final int LEAD_GEN_TTI = 11075671;
    public static final int MARKETPLACE_NOTIFICATIONS_TTI = 11075615;
    public static final int MAS_FBLITE_BSG_SELECT_BUYER_LOAD = 11075683;
    public static final int MESSAGING_TTI = 11075637;
    public static final int ML_PREFETCH_PREDICTION = 11075680;
    public static final short MODULE_ID = 169;
    public static final int MULTIPLE_PROFILE_SELLING_TTI = 11075626;
    public static final int OFFER_SHIPPING_NOT_ONBOARDED = 11080059;
    public static final int PDP_ANIMATION_TAP_LATENCY = 11075614;
    public static final int PDP_TTRC = 11075686;
    public static final int PENDING_TRANSACTIONS_TTI = 11075685;
    public static final int PRODUCT_DETAILS = 11075591;
    public static final int PRODUCT_DETAILS_LOAD = 11075613;
    public static final int PROFILE_TTI = 11075639;
    public static final int PROPERTY_FOR_RENT_TTI = 11075646;
    public static final int PROPERTY_FOR_SALE_TTI = 11075645;
    public static final int RECENTLY_VIEWED_TTI = 11075630;
    public static final int RELAY = 11075585;
    public static final int RELAY_MUTATION = 11075589;
    public static final int RENTAL_MAP_VIEW_TTI_MOBILE = 11075607;
    public static final int SAVED_ITEMS_TTI = 11075631;
    public static final int SAVED_LIST_ITEMS_TTI = 11075635;
    public static final int SEARCH_NULLSTATE_TTI = 11075600;
    public static final int SEARCH_PAGINATION_TTI = 11075652;
    public static final int SEARCH_RESULT_TTI = 11075595;
    public static final int SEARCH_RESULT_TTRC = 11075655;
    public static final int SELLER_FOLLOWER_TTI = 11075633;
    public static final int SELLER_INSIGHTS_TTRC = 11075679;
    public static final int SELLER_PAYMENT_INFO_TTI = 11075660;
    public static final int SERVICE_QUOTES_TTI = 11075634;
    public static final int SHIPPING_NUX_TTI = 11075656;
    public static final int SHOPS_TTI = 11075597;
    public static final int SURFACE_ROOT = 11075587;
    public static final int TAB_TTI = 11075596;
    public static final int UNIFIED_INVENTORY_TTI = 11075627;
    public static final int UNIFIED_INVENTORY_TTRC = 11075677;
    public static final int VEHICLES_CATEGORIES_SEARCH_TTI = 11075644;
    public static final int VEHICLES_SEARCH_TTI = 11075643;
    public static final int VERTICALS_CONTACTED_LISTINGS_TTI = 11075628;
    public static final int YOU_SALES_TTI = 11075657;
    public static final int YOU_TTI = 11075622;
    public static final int YOU_TTRC = 11075676;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "MARKETPLACE_RELAY";
        }
        if (i == 3) {
            return "MARKETPLACE_SURFACE_ROOT";
        }
        if (i == 20) {
            return "MARKETPLACE_COMPOSER_POST_TTI";
        }
        if (i == 21) {
            return "MARKETPLACE_B2C_PRODUCT_DETAILS";
        }
        if (i == 25) {
            return "MARKETPLACE_COMPOSER_EDIT_TTI";
        }
        if (i == 26) {
            return "MARKETPLACE_INDIA_HOME_LOAD";
        }
        if (i == 34) {
            return "MARKETPLACE_COMPOSER_TTI";
        }
        if (i == 35) {
            return "MARKETPLACE_EDIT_COMPOSER_TTI";
        }
        if (i == 46) {
            return "MARKETPLACE_RECENTLY_VIEWED_TTI";
        }
        if (i == 47) {
            return "MARKETPLACE_SAVED_ITEMS_TTI";
        }
        switch (i) {
            case 3:
                return "MARKETPLACE_SURFACE_ROOT";
            case 18:
                return "MARKETPLACE_FEED_AFTER_TTI_FIRST_PAGINATION_DELAY";
            case 23:
                return "MARKETPLACE_RENTAL_MAP_VIEW_TTI_MOBILE";
            case 55:
                return "MARKETPLACE_PROFILE_TTI";
            case 56:
                return "MARKETPLACE_BUY_SELL_GROUP_HOME_TTI";
            case 57:
                return "MARKETPLACE_FILTERED_CATEGORY_SEARCH_TTI";
            case 58:
                return "MARKETPLACE_HOME_SERVICES_TTI";
            case 59:
                return "MARKETPLACE_VEHICLES_SEARCH_TTI";
            case 60:
                return "MARKETPLACE_VEHICLES_CATEGORIES_SEARCH_TTI";
            case UL.id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                return "MARKETPLACE_PROPERTY_FOR_SALE_TTI";
            case 62:
                return "MARKETPLACE_PROPERTY_FOR_RENT_TTI";
            case 63:
                return "MARKETPLACE_DEBUG_SETTINGS_TTI";
            case 64:
                return "MARKETPLACE_HOME_TTRC";
            case 67:
                return "MARKETPLACE_HOME_PAGINATION_TTI";
            case 68:
                return "MARKETPLACE_SEARCH_PAGINATION_TTI";
            case 71:
                return "MARKETPLACE_SEARCH_RESULT_TTRC";
            case 72:
                return "MARKETPLACE_SHIPPING_NUX_TTI";
            case 73:
                return "MARKETPLACE_YOU_SALES_TTI";
            case 74:
                return "MARKETPLACE_CATEGORY_SURFACE_RESULT_TTRC";
            case 75:
                return "MARKETPLACE_BANK_ACCOUNT_EDIT_TTI";
            case 76:
                return "MARKETPLACE_SELLER_PAYMENT_INFO_TTI";
            case 77:
                return "MARKETPLACE_ECOMM_UNIFIED_LANDING_TTRC";
            case 78:
                return "MARKETPLACE_ECOMM_UNIFIED_LANDING_TTI";
            case 79:
                return "MARKETPLACE_ALL_TRANSACTIONS_TTI";
            case 80:
                return "MARKETPLACE_ALL_PAYOUTS_TTI";
            case 81:
                return "MARKETPLACE_DAILY_DEALS_TTRC";
            case 86:
                return "MARKETPLACE_FILTERS_TTI";
            case 87:
                return "MARKETPLACE_LEAD_GEN_TTI";
            case 88:
                return "MARKETPLACE_HOISTED_PDP_LOAD_TTRC";
            case 89:
                return "MARKETPLACE_CATEGORY_FEED_TTI";
            case 91:
                return "MARKETPLACE_CATEGORY_LISTING_VIEW_PAGINATION_TTI";
            case 92:
                return "MARKETPLACE_YOU_TTRC";
            case 93:
                return "MARKETPLACE_UNIFIED_INVENTORY_TTRC";
            case 94:
                return "MARKETPLACE_COMMERCE_PROFILE_TTRC";
            case 95:
                return "MARKETPLACE_SELLER_INSIGHTS_TTRC";
            case 96:
                return "MARKETPLACE_ML_PREFETCH_PREDICTION";
            case 98:
                return "MARKETPLACE_ECOMM_HOME_TTRC";
            case 99:
                return "MARKETPLACE_MAS_FBLITE_BSG_SELECT_BUYER_LOAD";
            case 101:
                return "MARKETPLACE_PENDING_TRANSACTIONS_TTI";
            case 102:
                return "MARKETPLACE_PDP_TTRC";
            case 3441:
                return "MARKETPLACE_HOMEFEED_FIRST_SCROLL";
            case 4475:
                return "MARKETPLACE_OFFER_SHIPPING_NOT_ONBOARDED";
            case 23669:
                return "MARKETPLACE_CATEGORY_FEED_TTRC";
            default:
                switch (i) {
                    case 5:
                        return "MARKETPLACE_RELAY_MUTATION";
                    case 6:
                        return "MARKETPLACE_FEED_LOAD";
                    case 7:
                        return "MARKETPLACE_PRODUCT_DETAILS";
                    case 8:
                        return "MARKETPLACE_FEED_IMAGE_LOAD";
                    case 9:
                        return "MARKETPLACE_HOME_TTI";
                    default:
                        switch (i) {
                            case 11:
                                return "MARKETPLACE_SEARCH_RESULT_TTI";
                            case 12:
                                return "MARKETPLACE_TAB_TTI";
                            case 13:
                                return "MARKETPLACE_SHOPS_TTI";
                            case 14:
                                return "MARKETPLACE_HOME_LOAD";
                            case 15:
                                return "MARKETPLACE_DAILY_DEALS_TTI";
                            case 16:
                                return "MARKETPLACE_SEARCH_NULLSTATE_TTI";
                            default:
                                switch (i) {
                                    case 28:
                                        return "MARKETPLACE_AUTOS_TTI";
                                    case 29:
                                        return "PRODUCT_DETAILS_LOAD";
                                    case 30:
                                        return "MARKETPLACE_PDP_ANIMATION_TAP_LATENCY";
                                    case 31:
                                        return "MARKETPLACE_MARKETPLACE_NOTIFICATIONS_TTI";
                                    case 32:
                                        return "MARKETPLACE_FEED_ITEM_PDP_MEASURE";
                                    default:
                                        switch (i) {
                                            case 37:
                                                return "MARKETPLACE_FOLLOWING_INFORMATION_TTI";
                                            case 38:
                                                return "MARKETPLACE_YOU_TTI";
                                            case 39:
                                                return "MARKETPLACE_BUYING_TTI";
                                            case 40:
                                                return "MARKETPLACE_FOLLOWING_TTI";
                                            case 41:
                                                return "MARKETPLACE_INBOX_TTI";
                                            case 42:
                                                return "MARKETPLACE_MULTIPLE_PROFILE_SELLING_TTI";
                                            case 43:
                                                return "MARKETPLACE_UNIFIED_INVENTORY_TTI";
                                            case 44:
                                                return "MARKETPLACE_VERTICALS_CONTACTED_LISTINGS_TTI";
                                            default:
                                                switch (i) {
                                                    case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                                        return "MARKETPLACE_SELLER_FOLLOWER_TTI";
                                                    case 50:
                                                        return "MARKETPLACE_SERVICE_QUOTES_TTI";
                                                    case 51:
                                                        return "MARKETPLACE_SAVED_LIST_ITEMS_TTI";
                                                    case 52:
                                                        return "MARKETPLACE_INITIAL_MESSAGING_TTI";
                                                    case 53:
                                                        return "MARKETPLACE_MESSAGING_TTI";
                                                    default:
                                                        return "UNDEFINED_QPL_EVENT";
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
