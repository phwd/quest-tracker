package com.facebook.quicklog.identifiers;

public class UserFunding {
    public static final int CS_WOODHENGE_ACTIVITY_TTI = 23461890;
    public static final int CS_WOODHENGE_CONFIRMATION_PAGE_TTI = 23461891;
    public static final int CS_WOODHENGE_SUPPORTER_PAGE_SETTINGS_TTI = 23461893;
    public static final int CS_WOODHENGE_SUPPORT_HUB_TTI = 23461892;
    public static final int IAP_PURCHASE_TTRC = 23461901;
    public static final short MODULE_ID = 358;
    public static final int NATIVE_PURCHASE_TTRC = 23461902;
    public static final int NT_SUBS_CONSIDERATION_FLOW = 23478269;
    public static final int STARS_BALANCE_TTRC = 23491362;
    public static final int STARS_BOTTOM_SHEET_TTL = 23461895;
    public static final int STARS_FBB_TTRC = 23461899;
    public static final int STARS_PROMO_PACK_TTRC = 23473227;
    public static final int STARS_PURCHASE_PAGE_TTRC = 23461900;
    public static final int STARS_SHEET_CONSIDERATION_PAGE_TTRC = 23461898;
    public static final int USER_EDUCATION_CARDS_TLL = 23461894;
    public static final int WOODHENGE_CONSIDERATION_PAGE_TTRC = 23461896;

    public static String getMarkerName(int i) {
        if (i == 11339) {
            return "USER_FUNDING_STARS_PROMO_PACK_TTRC";
        }
        if (i == 16381) {
            return "USER_FUNDING_NT_SUBS_CONSIDERATION_FLOW";
        }
        if (i == 29474) {
            return "USER_FUNDING_STARS_BALANCE_TTRC";
        }
        switch (i) {
            case 2:
                return "USER_FUNDING_CS_WOODHENGE_ACTIVITY_TTI";
            case 3:
                return "USER_FUNDING_CS_WOODHENGE_CONFIRMATION_PAGE_TTI";
            case 4:
                return "USER_FUNDING_CS_WOODHENGE_SUPPORT_HUB_TTI";
            case 5:
                return "USER_FUNDING_CS_WOODHENGE_SUPPORTER_PAGE_SETTINGS_TTI";
            case 6:
                return "USER_FUNDING_USER_EDUCATION_CARDS_TLL";
            case 7:
                return "USER_FUNDING_STARS_BOTTOM_SHEET_TTL";
            case 8:
                return "USER_FUNDING_WOODHENGE_CONSIDERATION_PAGE_TTRC";
            default:
                switch (i) {
                    case 10:
                        return "USER_FUNDING_STARS_SHEET_CONSIDERATION_PAGE_TTRC";
                    case 11:
                        return "USER_FUNDING_STARS_FBB_TTRC";
                    case 12:
                        return "USER_FUNDING_STARS_PURCHASE_PAGE_TTRC";
                    case 13:
                        return "USER_FUNDING_IAP_PURCHASE_TTRC";
                    case 14:
                        return "USER_FUNDING_NATIVE_PURCHASE_TTRC";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
