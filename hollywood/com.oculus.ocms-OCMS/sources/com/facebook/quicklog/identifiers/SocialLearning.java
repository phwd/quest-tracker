package com.facebook.quicklog.identifiers;

public class SocialLearning {
    public static final short MODULE_ID = 693;
    public static final int UNITS_LIST_TTRC = 45416449;
    public static final int UNIT_DETAILS_TTRC = 45416450;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "SOCIAL_LEARNING_UNIT_DETAILS_TTRC" : "SOCIAL_LEARNING_UNITS_LIST_TTRC";
    }
}
