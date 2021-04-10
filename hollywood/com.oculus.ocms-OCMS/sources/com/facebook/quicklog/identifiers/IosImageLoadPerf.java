package com.facebook.quicklog.identifiers;

public class IosImageLoadPerf {
    public static final int FBLITE_IOS_IMAGE_LOAD_PERF = 42598402;
    public static final short MODULE_ID = 650;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "IOS_IMAGE_LOAD_PERF_FBLITE_IOS_IMAGE_LOAD_PERF";
    }
}
