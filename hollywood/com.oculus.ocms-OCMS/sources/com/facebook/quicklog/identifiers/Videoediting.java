package com.facebook.quicklog.identifiers;

public class Videoediting {
    public static final short MODULE_ID = 140;
    public static final int PERF_INIT_FROM_COMPOSER_TTI = 9175041;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "VIDEOEDITING_PERF_INIT_FROM_COMPOSER_TTI";
    }
}
