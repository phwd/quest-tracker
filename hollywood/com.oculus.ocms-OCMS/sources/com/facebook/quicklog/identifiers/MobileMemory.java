package com.facebook.quicklog.identifiers;

public class MobileMemory {
    public static final int MOBILE_MEMORY_LEAK_METRICS = 50336152;
    public static final int MOBILE_MEMORY_METRICS = 50331649;
    public static final short MODULE_ID = 768;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 4504 ? "UNDEFINED_QPL_EVENT" : "MOBILE_MEMORY_MOBILE_MEMORY_LEAK_METRICS" : "MOBILE_MEMORY_MOBILE_MEMORY_METRICS";
    }
}
