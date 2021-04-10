package com.facebook.quicklog.identifiers;

public class WorkplaceForGood {
    public static final short MODULE_ID = 1480;
    public static final int TOKEN_SUBMITTED = 96997416;

    public static String getMarkerName(int i) {
        return i != 4136 ? "UNDEFINED_QPL_EVENT" : "WORKPLACE_FOR_GOOD_TOKEN_SUBMITTED";
    }
}
