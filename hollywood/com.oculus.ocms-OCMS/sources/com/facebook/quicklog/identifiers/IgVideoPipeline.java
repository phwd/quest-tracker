package com.facebook.quicklog.identifiers;

public class IgVideoPipeline {
    public static final short MODULE_ID = 941;
    public static final int PREFETCH = 61669377;
    public static final int REPORT_VIDEO_BLACKSCREEN_TIME = 61673387;
    public static final int VIDEO_BLACKSCREEN_DETECTED = 61683940;
    public static final int WARMUP = 61669378;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 4011 ? i != 14564 ? "UNDEFINED_QPL_EVENT" : "IG_VIDEO_PIPELINE_VIDEO_BLACKSCREEN_DETECTED" : "IG_VIDEO_PIPELINE_REPORT_VIDEO_BLACKSCREEN_TIME" : "IG_VIDEO_PIPELINE_WARMUP" : "IG_VIDEO_PIPELINE_PREFETCH";
    }
}
