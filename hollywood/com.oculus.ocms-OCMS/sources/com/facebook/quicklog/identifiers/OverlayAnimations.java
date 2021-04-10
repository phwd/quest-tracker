package com.facebook.quicklog.identifiers;

public class OverlayAnimations {
    public static final int ANIMATION_FINISH = 16711682;
    public static final int ANIMATION_START = 16711681;
    public static final int ANIMATION_TRIGGERED = 16711683;
    public static final short MODULE_ID = 255;
    public static final int PLAY_ANIMATION = 16711684;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "OVERLAY_ANIMATIONS_PLAY_ANIMATION" : "OVERLAY_ANIMATIONS_ANIMATION_TRIGGERED" : "OVERLAY_ANIMATIONS_ANIMATION_FINISH" : "OVERLAY_ANIMATIONS_ANIMATION_START";
    }
}
