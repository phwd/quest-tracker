package com.facebook.quicklog.identifiers;

public class FrescoInstrumentation {
    public static final int FRESCO_CACHE_OBSERVER = 41222146;
    public static final int FRESCO_IMAGE_PERF = 41222145;
    public static final int FRESCO_IMAGE_PERF_ADHOC = 41228632;
    public static final short MODULE_ID = 629;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 6488 ? "UNDEFINED_QPL_EVENT" : "FRESCO_INSTRUMENTATION_FRESCO_IMAGE_PERF_ADHOC" : "FRESCO_INSTRUMENTATION_FRESCO_CACHE_OBSERVER" : "FRESCO_INSTRUMENTATION_FRESCO_IMAGE_PERF";
    }
}
