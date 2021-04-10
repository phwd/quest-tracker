package com.facebook.quicklog.identifiers;

public class LiveVideoPlayer {
    public static final int ENTRY_LOAD_WATERFALL = 49610753;
    public static final short MODULE_ID = 757;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "LIVE_VIDEO_PLAYER_ENTRY_LOAD_WATERFALL";
    }
}
