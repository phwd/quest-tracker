package com.facebook.quicklog.identifiers;

public class WpBroadcastVideo {
    public static final int LIVE_STREAM = 54198274;
    public static final short MODULE_ID = 827;
    public static final int MULTI_PRESENTERS_LIVE = 54203833;
    public static final int MULTI_PRESENTERS_LIVE_ROOM = 54205001;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 5561 ? i != 6729 ? "UNDEFINED_QPL_EVENT" : "WP_BROADCAST_VIDEO_MULTI_PRESENTERS_LIVE_ROOM" : "WP_BROADCAST_VIDEO_MULTI_PRESENTERS_LIVE" : "WP_BROADCAST_VIDEO_LIVE_STREAM";
    }
}
