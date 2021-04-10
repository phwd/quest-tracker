package com.facebook.quicklog.identifiers;

public class CheckoutPurchaseExperiences {
    public static final int CHECKOUT_FLOW = 23265281;
    public static final int CHECKOUT_INFO_API_IG = 23265290;
    public static final int CHECKOUT_LAUNCH_BY_PRODUCT = 23265282;
    public static final int CHECKOUT_TTI = 23273330;
    public static final short MODULE_ID = 355;
    public static final int PAY_FLOW = 23265283;
    public static final int PAY_FLOW_IG = 23265288;
    public static final int PLACE_ORDER_TTI = 23281173;
    public static final int UPDATE_CHECKOUT_API_IG = 23265289;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "CHECKOUT_PURCHASE_EXPERIENCES_CHECKOUT_FLOW";
        }
        if (i == 2) {
            return "CHECKOUT_PURCHASE_EXPERIENCES_CHECKOUT_LAUNCH_BY_PRODUCT";
        }
        if (i == 3) {
            return "CHECKOUT_PURCHASE_EXPERIENCES_PAY_FLOW";
        }
        if (i == 8050) {
            return "CHECKOUT_PURCHASE_EXPERIENCES_CHECKOUT_TTI";
        }
        if (i == 15893) {
            return "CHECKOUT_PURCHASE_EXPERIENCES_PLACE_ORDER_TTI";
        }
        switch (i) {
            case 8:
                return "CHECKOUT_PURCHASE_EXPERIENCES_PAY_FLOW_IG";
            case 9:
                return "CHECKOUT_PURCHASE_EXPERIENCES_UPDATE_CHECKOUT_API_IG";
            case 10:
                return "CHECKOUT_PURCHASE_EXPERIENCES_CHECKOUT_INFO_API_IG";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
