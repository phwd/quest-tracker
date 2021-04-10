package com.facebook.quicklog.identifiers;

public class FosMonetization {
    public static final int FOS_MON_PURCHASE_FLOW_FUNNEL = 725614593;
    public static final short MODULE_ID = 11072;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FOS_MONETIZATION_FOS_MON_PURCHASE_FLOW_FUNNEL";
    }
}
