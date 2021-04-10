package com.facebook.quicklog.identifiers;

public class AndroidImageLoadPerf {
    public static final int IMAGE_LIFECYCLE = 42680150;
    public static final int IMAGE_LOAD_PERF = 42663937;
    public static final int IMAGE_LOAD_PERF_DETAILED = 42673451;
    public static final short MODULE_ID = 651;
    public static final int VIEW_DISAPPEARED = 42663938;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 9515 ? i != 16214 ? "UNDEFINED_QPL_EVENT" : "ANDROID_IMAGE_LOAD_PERF_IMAGE_LIFECYCLE" : "ANDROID_IMAGE_LOAD_PERF_IMAGE_LOAD_PERF_DETAILED" : "ANDROID_IMAGE_LOAD_PERF_VIEW_DISAPPEARED" : "ANDROID_IMAGE_LOAD_PERF_IMAGE_LOAD_PERF";
    }
}
