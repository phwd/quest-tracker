package com.facebook.quicklog.identifiers;

public class ReactNativeSegmentTimings {
    public static final short MODULE_ID = 729;
    public static final int SEGMENT_FETCH_TIMING = 47775746;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "REACT_NATIVE_SEGMENT_TIMINGS_SEGMENT_FETCH_TIMING";
    }
}
