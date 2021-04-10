package com.facebook.quicklog.identifiers;

public class ConnectedCommerce {
    public static final short MODULE_ID = 812;
    public static final int PAGE_SHOP_CARD_LOAD = 53215237;
    public static final int PAGE_SHOP_LOAD = 53215235;
    public static final int PAGE_SHOP_PAGINATE = 53215236;
    public static final int SDS_GEN_ALL_COLLECTION = 53238567;
    public static final int SDS_MORE_PRODUCTS_FROM_SHOP_PIVOT_LOAD = 53215234;
    public static final int SDS_MORE_PRODUCTS_FROM_SHOP_PIVOT_PAGINATE = 53215240;
    public static final int SDS_STORY_VIEW_LOAD = 53215239;
    public static final int SHOP_AND_DISCOVER_LOAD = 53215233;
    public static final int SHOP_AND_DISCOVER_TTI = 53215238;
    public static final int SHOP_AND_DISCOVER_TTRC = 53215241;

    public static String getMarkerName(int i) {
        if (i == 23335) {
            return "CONNECTED_COMMERCE_SDS_GEN_ALL_COLLECTION";
        }
        switch (i) {
            case 1:
                return "CONNECTED_COMMERCE_SHOP_AND_DISCOVER_LOAD";
            case 2:
                return "CONNECTED_COMMERCE_SDS_MORE_PRODUCTS_FROM_SHOP_PIVOT_LOAD";
            case 3:
                return "CONNECTED_COMMERCE_PAGE_SHOP_LOAD";
            case 4:
                return "CONNECTED_COMMERCE_PAGE_SHOP_PAGINATE";
            case 5:
                return "CONNECTED_COMMERCE_PAGE_SHOP_CARD_LOAD";
            case 6:
                return "CONNECTED_COMMERCE_SHOP_AND_DISCOVER_TTI";
            case 7:
                return "CONNECTED_COMMERCE_SDS_STORY_VIEW_LOAD";
            case 8:
                return "CONNECTED_COMMERCE_SDS_MORE_PRODUCTS_FROM_SHOP_PIVOT_PAGINATE";
            case 9:
                return "CONNECTED_COMMERCE_SHOP_AND_DISCOVER_TTRC";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
