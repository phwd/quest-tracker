package com.facebook.quicklog.identifiers;

public class IgComments {
    public static final int IG_COMMENTS_LOAD = 16646145;
    public static final short MODULE_ID = 254;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_COMMENTS_IG_COMMENTS_LOAD";
    }
}
