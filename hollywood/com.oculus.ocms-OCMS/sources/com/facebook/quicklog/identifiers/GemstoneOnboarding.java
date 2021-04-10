package com.facebook.quicklog.identifiers;

public class GemstoneOnboarding {
    public static final int GEMSTONE_ONBOARDING_TTRC_ANDROID = 41877506;
    public static final int GEMSTONE_ONBOARDING_TTRC_IOS = 41877505;
    public static final short MODULE_ID = 639;
    public static final int ONBOARDING_WELCOME_TTRC_FB4A_IOS = 41877507;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "Dating onboarding TTRC marker" : "GEMSTONE_ONBOARDING_GEMSTONE_ONBOARDING_TTRC_ANDROID" : "GEMSTONE_ONBOARDING_GEMSTONE_ONBOARDING_TTRC_IOS";
    }
}
