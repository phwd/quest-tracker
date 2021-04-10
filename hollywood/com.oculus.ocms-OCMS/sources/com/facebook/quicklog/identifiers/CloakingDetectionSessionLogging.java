package com.facebook.quicklog.identifiers;

public class CloakingDetectionSessionLogging {
    public static final int BROWSER_EXTRACTION = 49020929;
    public static final short MODULE_ID = 748;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CLOAKING_DETECTION_SESSION_LOGGING_BROWSER_EXTRACTION";
    }
}
