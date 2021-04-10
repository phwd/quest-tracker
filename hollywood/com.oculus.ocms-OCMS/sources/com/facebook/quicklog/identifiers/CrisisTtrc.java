package com.facebook.quicklog.identifiers;

public class CrisisTtrc {
    public static final int CRISIS_PAGE_TTRC_ANDROID_IOS = 38010881;
    public static final short MODULE_ID = 580;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CRISIS_TTRC_CRISIS_PAGE_TTRC_ANDROID_IOS";
    }
}
