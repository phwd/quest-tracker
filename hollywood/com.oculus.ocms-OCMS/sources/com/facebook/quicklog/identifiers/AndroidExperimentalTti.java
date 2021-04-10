package com.facebook.quicklog.identifiers;

public class AndroidExperimentalTti {
    public static final int ANDROID_EXPERIMENTAL_TTI = 23986177;
    public static final short MODULE_ID = 366;
    public static final int ZHICHENG_TEST = 23986178;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "ANDROID_EXPERIMENTAL_TTI_ZHICHENG_TEST" : "ANDROID_EXPERIMENTAL_TTI_ANDROID_EXPERIMENTAL_TTI";
    }
}
