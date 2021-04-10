package com.facebook.quicklog.identifiers;

public class GroupsTab {
    public static final int ALL_HEADER_SECTIONS_LOAD = 7864331;
    public static final int COLLECTION_MALL_TTRC = 7864381;
    public static final int COLLECTION_TTRC = 7864379;
    public static final int DISCOVER_CATEGORIES_TTI = 7864341;
    public static final int DISCOVER_CATEGORIES_TTRC = 7864357;
    public static final int DISCOVER_CATEGORY_TTI = 7864339;
    public static final int DISCOVER_CATEGORY_TTRC = 7864353;
    public static final int DISCOVER_LANDING_TTI = 7864338;
    public static final int DISCOVER_LANDING_TTRC = 7864348;
    public static final int EDIT_GROUP_LIST_TTI = 7864346;
    public static final int FAVORITE_GROUPS_FULL_LIST = 7864332;
    public static final int FAVORITE_GROUPS_FULL_LIST_IN_FAV_ONLY_FRAGMENT = 7864334;
    public static final int FAVORITE_GROUPS_SECTION_LOAD = 7864323;
    public static final int FIRST_HEADER_SECTION_LOAD = 7864330;
    public static final int FULL_GROUP_LIST_TTI = 7864335;
    public static final int FULL_GROUP_LIST_TTRC = 7864352;
    public static final int GROUPS_DISCOVER_TAB_TAIL_FETCH_RESTORATION_ANDROID = 7864373;
    public static final int GROUPS_TAB_CSR_TAIL_LOAD = 7864383;
    public static final int GROUPS_TAB_TAIL_LOAD = 7864366;
    public static final int GROUP_TAB_TTRC = 7864351;
    public static final int GYSJ_PREVIEW_TTRC = 7864378;
    public static final int HSCROLL_GROUPS_LIST_SECTION_LOAD = 7864329;
    public static final int MAIN_FRAGMENT_LIFECYCLE = 7864336;
    public static final int MERGED_GROUPS_LIST_SECTION_LOAD = 7864328;
    public static final short MODULE_ID = 120;
    public static final int MY_GROUPS_PAGINATE = 7864384;
    public static final int NON_FAVORITE_GROUPS_FULL_LIST = 7864333;
    public static final int NON_FAVORITE_GROUPS_SECTION_LOAD = 7864324;
    public static final int PTR = 7864340;
    public static final int SETTINGS_TAB_TTRC = 7864369;
    public static final int TAIL_LOAD = 7864337;
    public static final int TAIL_LOAD_V2 = 7864376;
    public static final int TTI = 7864321;
    public static final int USER_FLOW = 7865477;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "GROUPS_TAB_TTI";
        }
        if (i == 26) {
            return "GROUPS_TAB_EDIT_GROUP_LIST_TTI";
        }
        if (i == 28) {
            return "GROUPS_TAB_DISCOVER_LANDING_TTRC";
        }
        if (i == 37) {
            return "GROUPS_TAB_DISCOVER_CATEGORIES_TTRC";
        }
        if (i == 46) {
            return "GROUPS_TAB_GROUPS_TAB_TAIL_LOAD";
        }
        if (i == 49) {
            return "GROUPS_TAB_SETTINGS_TAB_TTRC";
        }
        if (i == 53) {
            return "GROUPS_TAB_GROUPS_DISCOVER_TAB_TAIL_FETCH_RESTORATION_ANDROID";
        }
        if (i == 56) {
            return "GROUPS_TAB_TAIL_LOAD_V2";
        }
        if (i == 61) {
            return "GROUPS_TAB_COLLECTION_MALL_TTRC";
        }
        if (i == 1157) {
            return "GROUPS_TAB_USER_FLOW";
        }
        if (i == 3) {
            return "GROUPS_TAB_FAVORITE_GROUPS_SECTION_LOAD";
        }
        if (i == 4) {
            return "GROUPS_TAB_NON_FAVORITE_GROUPS_SECTION_LOAD";
        }
        if (i == 58) {
            return "GROUPS_TAB_GYSJ_PREVIEW_TTRC";
        }
        if (i == 59) {
            return "GROUPS_TAB_COLLECTION_TTRC";
        }
        if (i == 63) {
            return "GROUPS_TAB_GROUPS_TAB_CSR_TAIL_LOAD";
        }
        if (i == 64) {
            return "GROUPS_TAB_MY_GROUPS_PAGINATE";
        }
        switch (i) {
            case 8:
                return "GROUPS_TAB_MERGED_GROUPS_LIST_SECTION_LOAD";
            case 9:
                return "GROUPS_TAB_HSCROLL_GROUPS_LIST_SECTION_LOAD";
            case 10:
                return "GROUPS_TAB_FIRST_HEADER_SECTION_LOAD";
            case 11:
                return "GROUPS_TAB_ALL_HEADER_SECTIONS_LOAD";
            case 12:
                return "GROUPS_TAB_FAVORITE_GROUPS_FULL_LIST";
            case 13:
                return "GROUPS_TAB_NON_FAVORITE_GROUPS_FULL_LIST";
            case 14:
                return "GROUPS_TAB_FAVORITE_GROUPS_FULL_LIST_IN_FAV_ONLY_FRAGMENT";
            case 15:
                return "GROUPS_TAB_FULL_GROUP_LIST_TTI";
            case 16:
                return "GROUPS_TAB_MAIN_FRAGMENT_LIFECYCLE";
            case 17:
                return "GROUPS_TAB_TAIL_LOAD";
            case 18:
                return "GROUPS_TAB_DISCOVER_LANDING_TTI";
            case 19:
                return "GROUPS_TAB_DISCOVER_CATEGORY_TTI";
            case 20:
                return "GROUPS_TAB_PTR";
            case 21:
                return "GROUPS_TAB_DISCOVER_CATEGORIES_TTI";
            default:
                switch (i) {
                    case 31:
                        return "GROUPS_TAB_GROUP_TAB_TTRC";
                    case 32:
                        return "GROUPS_TAB_FULL_GROUP_LIST_TTRC";
                    case 33:
                        return "GROUPS_TAB_DISCOVER_CATEGORY_TTRC";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
