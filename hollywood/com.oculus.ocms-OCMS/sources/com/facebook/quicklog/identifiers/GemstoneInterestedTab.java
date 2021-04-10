package com.facebook.quicklog.identifiers;

public class GemstoneInterestedTab {
    public static final int GEMSTONE_INTERESTED_TAB_TTRC_ANDROID = 30932993;
    public static final int GEMSTONE_INTERESTED_TAB_TTRC_IOS = 30932994;
    public static final short MODULE_ID = 472;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "GEMSTONE_INTERESTED_TAB_GEMSTONE_INTERESTED_TAB_TTRC_IOS" : "GEMSTONE_INTERESTED_TAB_GEMSTONE_INTERESTED_TAB_TTRC_ANDROID";
    }
}
