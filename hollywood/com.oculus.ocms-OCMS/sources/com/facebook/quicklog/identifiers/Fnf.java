package com.facebook.quicklog.identifiers;

public class Fnf {
    public static final int FB4A_VIDEO_AUTOPLAY_RUNNABLE = 19464213;
    public static final int FB4A_VIDEO_PLAYER_EVENT_DISPATCH = 19464212;
    public static final short MODULE_ID = 297;

    public static String getMarkerName(int i) {
        return i != 20 ? i != 21 ? "UNDEFINED_QPL_EVENT" : "FNF_FB4A_VIDEO_AUTOPLAY_RUNNABLE" : "FNF_FB4A_VIDEO_PLAYER_EVENT_DISPATCH";
    }
}
