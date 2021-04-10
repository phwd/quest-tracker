package com.facebook.quicklog.identifiers;

public class AdsAnimator {
    public static final short MODULE_ID = 390;
    public static final int PREVIEW_TTI_ANDROID = 25559041;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ADS_ANIMATOR_PREVIEW_TTI_ANDROID";
    }
}
