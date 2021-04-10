package com.facebook.quicklog.identifiers;

public class ImagepipelineBoost {
    public static final int IMAGEPIPELINE_PRODUCER_ANDROID = 33357825;
    public static final short MODULE_ID = 509;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IMAGEPIPELINE_BOOST_IMAGEPIPELINE_PRODUCER_ANDROID";
    }
}
