package com.facebook.quicklog.identifiers;

public class IgImagePipeline {
    public static final int IMAGE_DOWNLOAD = 23396353;
    public static final int IMAGE_RENDER_LATENCY = 23410213;
    public static final short MODULE_ID = 357;
    public static final int ON_SCREEN_IMAGE_LOAD_TIME = 23396355;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3 ? i != 13861 ? "UNDEFINED_QPL_EVENT" : "IG_IMAGE_PIPELINE_IMAGE_RENDER_LATENCY" : "IG_IMAGE_PIPELINE_ON_SCREEN_IMAGE_LOAD_TIME" : "IG_IMAGE_PIPELINE_IMAGE_DOWNLOAD";
    }
}
