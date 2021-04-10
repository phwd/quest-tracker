package com.facebook.quicklog.identifiers;

public class MobileHome {
    public static final int COLD_START_TTI = 17760257;
    public static final int HOME_COLD_START_TTRC = 17760258;
    public static final short MODULE_ID = 271;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "MOBILE_HOME_HOME_COLD_START_TTRC" : "MOBILE_HOME_COLD_START_TTI";
    }
}
