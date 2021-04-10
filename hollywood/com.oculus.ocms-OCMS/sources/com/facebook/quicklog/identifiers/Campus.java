package com.facebook.quicklog.identifiers;

public class Campus {
    public static final int CAMPUS_HOME_TTRC_ANDROID = 57016322;
    public static final int CAMPUS_ONBOARDING_TTRC = 57024644;
    public static final short MODULE_ID = 870;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 8324 ? "UNDEFINED_QPL_EVENT" : "CAMPUS_CAMPUS_ONBOARDING_TTRC" : "CAMPUS_CAMPUS_HOME_TTRC_ANDROID";
    }
}
