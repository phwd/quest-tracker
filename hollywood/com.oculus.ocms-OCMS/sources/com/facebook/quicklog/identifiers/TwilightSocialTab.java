package com.facebook.quicklog.identifiers;

public class TwilightSocialTab {
    public static final short MODULE_ID = 830;
    public static final int SOCIAL_COLD_START_TTI = 54394881;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "TWILIGHT_SOCIAL_TAB_SOCIAL_COLD_START_TTI";
    }
}
