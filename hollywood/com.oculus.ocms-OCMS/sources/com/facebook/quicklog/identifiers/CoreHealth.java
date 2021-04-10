package com.facebook.quicklog.identifiers;

public class CoreHealth {
    public static final int ANR_DETECT = 15859713;
    public static final int ANR_DETECT_DATA_CAPTURE = 15859714;
    public static final int JAVA_CRASH = 15859715;
    public static final short MODULE_ID = 242;
    public static final int NATIVE_CRASH = 15859716;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "CORE_HEALTH_NATIVE_CRASH" : "CORE_HEALTH_JAVA_CRASH" : "CORE_HEALTH_ANR_DETECT_DATA_CAPTURE" : "CORE_HEALTH_ANR_DETECT";
    }
}
