package com.facebook.quicklog.identifiers;

public class IgcarbonMediaPerf {
    public static final int IMAGE_FETCH = 50593794;
    public static final int IMAGE_LOAD = 50593793;
    public static final short MODULE_ID = 772;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "IGCARBON_MEDIA_PERF_IMAGE_FETCH" : "IGCARBON_MEDIA_PERF_IMAGE_LOAD";
    }
}
