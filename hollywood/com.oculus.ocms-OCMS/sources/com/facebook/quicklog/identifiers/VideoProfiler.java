package com.facebook.quicklog.identifiers;

public class VideoProfiler {
    public static final int ABR_DECISION = 28180481;
    public static final int HTTP_TRANSFER_EVENT = 28180482;
    public static final short MODULE_ID = 430;
    public static final int VIDEO_PLAYBACK_STATE = 28180483;
    public static final int VIDEO_QUALITIES = 28180484;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "VIDEO_PROFILER_VIDEO_QUALITIES" : "VIDEO_PROFILER_VIDEO_PLAYBACK_STATE" : "VIDEO_PROFILER_HTTP_TRANSFER_EVENT" : "VIDEO_PROFILER_ABR_DECISION";
    }
}
