package com.facebook.quicklog.identifiers;

public class ProfileImagePerf {
    public static final short MODULE_ID = 316;
    public static final int PROFILE_IMAGE_LOAD = 20709377;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "PROFILE_IMAGE_PERF_PROFILE_IMAGE_LOAD";
    }
}
