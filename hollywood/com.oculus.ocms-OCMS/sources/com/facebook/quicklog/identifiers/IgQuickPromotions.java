package com.facebook.quicklog.identifiers;

public class IgQuickPromotions {
    public static final int IG_QP_RESOLUTION_DURATION = 35061762;
    public static final short MODULE_ID = 535;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "IG_QUICK_PROMOTIONS_IG_QP_RESOLUTION_DURATION";
    }
}
