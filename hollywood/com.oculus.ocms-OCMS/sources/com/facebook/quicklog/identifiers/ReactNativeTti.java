package com.facebook.quicklog.identifiers;

public class ReactNativeTti {
    public static final int ADMIN_SURFACE_TTI = 13828113;
    public static final int ADS_PAYMENTS_TTI = 13828101;
    public static final int BLOOD_REQUEST_TTI = 13828121;
    public static final int CRISIS_BOOKMARK_TTI = 13828120;
    public static final int CRISIS_TTI = 13828097;
    public static final int DEFAULT_TTI = 13828098;
    public static final int EDIT_PROFILE_TTI = 13828111;
    public static final int FOOD_DRINK_TTI = 13828109;
    public static final int GROUPS_PENDING_POSTS_TTI = 13828115;
    public static final int JOBS_APPLICATION_FORM_TTI = 13828107;
    public static final int JOBS_COMPOSER_TTI = 13828108;
    public static final int JOBS_JOB_BROWSER_TTI = 13828103;
    public static final int JOBS_JOB_OPENING_DETAIL_VIEW_TTI = 13828106;
    public static final int JOBS_SAVED_FOR_LATER_TTI = 13828117;
    public static final int LIVE_SHOPPING_TTI = 13841792;
    public static final int LOYALTY_HOME_TTI = 13828123;
    public static final int MCOM_RN_ORDER_MANAGEMENT_TTI = 13828128;
    public static final int MENU_PDP_TTI = 13831515;
    public static final short MODULE_ID = 211;
    public static final int NEO_CONTACT_LIST_TTI = 13828124;
    public static final int NEO_PROFILE_TTI = 13828118;
    public static final int OFFERS_TTI = 13828102;
    public static final int PRIVACY_AYI_TTI = 13828112;
    public static final int PRIVACY_BLOCKING_REQUEST_TTI = 13828104;
    public static final int PRIVACY_BLOCKING_SEARCH_TTI = 13828105;
    public static final int PRIVACY_SHORTCUTS_TTI = 13828127;
    public static final int RN_LIGHTWEIGHT_INTERFACES_TTI = 13828119;
    public static final int SIREN_ITEM_TTI = 13828100;
    public static final int SIREN_LIST_TTI = 13828099;
    public static final int TIME_IN_APP_2_TTI = 13828126;
    public static final int TIME_IN_APP_TTI = 13828125;

    public static String getMarkerName(int i) {
        if (i == 19) {
            return "REACT_NATIVE_TTI_GROUPS_PENDING_POSTS_TTI";
        }
        if (i == 3419) {
            return "REACT_NATIVE_TTI_MENU_PDP_TTI";
        }
        if (i == 13696) {
            return "REACT_NATIVE_TTI_LIVE_SHOPPING_TTI";
        }
        switch (i) {
            case 1:
                return "REACT_NATIVE_TTI_CRISIS_TTI";
            case 2:
                return "REACT_NATIVE_TTI_DEFAULT_TTI";
            case 3:
                return "REACT_NATIVE_TTI_SIREN_LIST_TTI";
            case 4:
                return "REACT_NATIVE_TTI_SIREN_ITEM_TTI";
            case 5:
                return "REACT_NATIVE_TTI_ADS_PAYMENTS_TTI";
            case 6:
                return "REACT_NATIVE_TTI_OFFERS_TTI";
            case 7:
                return "REACT_NATIVE_TTI_JOBS_JOB_BROWSER_TTI";
            case 8:
                return "REACT_NATIVE_TTI_PRIVACY_BLOCKING_REQUEST_TTI";
            case 9:
                return "REACT_NATIVE_TTI_PRIVACY_BLOCKING_SEARCH_TTI";
            case 10:
                return "REACT_NATIVE_TTI_JOBS_JOB_OPENING_DETAIL_VIEW_TTI";
            case 11:
                return "REACT_NATIVE_TTI_JOBS_APPLICATION_FORM_TTI";
            case 12:
                return "REACT_NATIVE_TTI_JOBS_COMPOSER_TTI";
            case 13:
                return "REACT_NATIVE_TTI_FOOD_DRINK_TTI";
            default:
                switch (i) {
                    case 15:
                        return "REACT_NATIVE_TTI_EDIT_PROFILE_TTI";
                    case 16:
                        return "REACT_NATIVE_TTI_PRIVACY_AYI_TTI";
                    case 17:
                        return "REACT_NATIVE_TTI_ADMIN_SURFACE_TTI";
                    default:
                        switch (i) {
                            case 21:
                                return "REACT_NATIVE_TTI_JOBS_SAVED_FOR_LATER_TTI";
                            case 22:
                                return "REACT_NATIVE_TTI_NEO_PROFILE_TTI";
                            case 23:
                                return "REACT_NATIVE_TTI_RN_LIGHTWEIGHT_INTERFACES_TTI";
                            case 24:
                                return "REACT_NATIVE_TTI_CRISIS_BOOKMARK_TTI";
                            case 25:
                                return "REACT_NATIVE_TTI_BLOOD_REQUEST_TTI";
                            default:
                                switch (i) {
                                    case 27:
                                        return "REACT_NATIVE_TTI_LOYALTY_HOME_TTI";
                                    case 28:
                                        return "REACT_NATIVE_TTI_NEO_CONTACT_LIST_TTI";
                                    case 29:
                                        return "REACT_NATIVE_TTI_TIME_IN_APP_TTI";
                                    case 30:
                                        return "REACT_NATIVE_TTI_TIME_IN_APP_2_TTI";
                                    case 31:
                                        return "REACT_NATIVE_TTI_PRIVACY_SHORTCUTS_TTI";
                                    case 32:
                                        return "REACT_NATIVE_TTI_MCOM_RN_ORDER_MANAGEMENT_TTI";
                                    default:
                                        return "UNDEFINED_QPL_EVENT";
                                }
                        }
                }
        }
    }
}
