package com.facebook.quicklog.identifiers;

public class LassoAndroidColdStart {
    public static final short MODULE_ID = 441;
    public static final int TTI_ANDROID = 28901377;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "LASSO_ANDROID_COLD_START_TTI_ANDROID";
    }
}
