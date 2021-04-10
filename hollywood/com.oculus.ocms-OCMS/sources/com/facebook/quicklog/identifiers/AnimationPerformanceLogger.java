package com.facebook.quicklog.identifiers;

public class AnimationPerformanceLogger {
    public static final int FRAME_DURATION = 16515078;
    public static final short MODULE_ID = 252;

    public static String getMarkerName(int i) {
        return i != 6 ? "UNDEFINED_QPL_EVENT" : "ANIMATION_PERFORMANCE_LOGGER_FRAME_DURATION";
    }
}
