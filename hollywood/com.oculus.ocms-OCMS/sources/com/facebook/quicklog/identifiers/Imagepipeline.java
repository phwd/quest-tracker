package com.facebook.quicklog.identifiers;

public class Imagepipeline {
    public static final int EFFICIENCY_TRACKER_NET_FETCH = 1179651;
    public static final int EFFICIENCY_TRACKER_REQUEST_SUCCESS = 1179652;
    public static final short MODULE_ID = 18;
    public static final int REQUEST_PERF = 1179653;
    public static final int STREAMED_REQUEST = 1179650;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "IMAGEPIPELINE_REQUEST_PERF" : "IMAGEPIPELINE_EFFICIENCY_TRACKER_REQUEST_SUCCESS" : "IMAGEPIPELINE_EFFICIENCY_TRACKER_NET_FETCH" : "IMAGEPIPELINE_STREAMED_REQUEST";
    }
}
