package com.facebook.quicklog.identifiers;

public class AnimationPerf {
    public static final int ANIMATION_PLAYING = 38141953;
    public static final short MODULE_ID = 582;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ANIMATION_PERF_ANIMATION_PLAYING";
    }
}
