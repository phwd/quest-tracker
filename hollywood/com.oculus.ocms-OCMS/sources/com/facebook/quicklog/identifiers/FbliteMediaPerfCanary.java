package com.facebook.quicklog.identifiers;

public class FbliteMediaPerfCanary {
    public static final int IMAGE_LOAD = 172755431;
    public static final short MODULE_ID = 2636;

    public static String getMarkerName(int i) {
        return i != 2535 ? "UNDEFINED_QPL_EVENT" : "FBLITE_MEDIA_PERF_CANARY_IMAGE_LOAD";
    }
}
