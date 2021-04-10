package com.facebook.quicklog.identifiers;

public class IgReels {
    public static final int IG_AUDIO_PAGE_LOAD = 658060488;
    public static final int IG_EFFECT_PAGE_LOAD = 658048518;
    public static final short MODULE_ID = 10041;

    public static String getMarkerName(int i) {
        return i != 1542 ? i != 13512 ? "UNDEFINED_QPL_EVENT" : "IG_REELS_IG_AUDIO_PAGE_LOAD" : "IG_REELS_IG_EFFECT_PAGE_LOAD";
    }
}
