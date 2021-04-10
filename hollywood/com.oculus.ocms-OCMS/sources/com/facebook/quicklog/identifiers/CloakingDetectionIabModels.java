package com.facebook.quicklog.identifiers;

public class CloakingDetectionIabModels {
    public static final int MODELS_RUN = 48889857;
    public static final short MODULE_ID = 746;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CLOAKING_DETECTION_IAB_MODELS_MODELS_RUN";
    }
}
