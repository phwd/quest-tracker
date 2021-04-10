package com.facebook.quicklog.identifiers;

public class CloudGaming {
    public static final int INIT_DECODER = 378805070;
    public static final short MODULE_ID = 5780;

    public static String getMarkerName(int i) {
        return i != 6990 ? "UNDEFINED_QPL_EVENT" : "CLOUD_GAMING_INIT_DECODER";
    }
}
