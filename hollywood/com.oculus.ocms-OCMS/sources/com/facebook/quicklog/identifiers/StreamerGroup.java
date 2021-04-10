package com.facebook.quicklog.identifiers;

public class StreamerGroup {
    public static final int LATEST_VIDEOS_NT = 97593564;
    public static final short MODULE_ID = 1489;

    public static String getMarkerName(int i) {
        return i != 10460 ? "UNDEFINED_QPL_EVENT" : "STREAMER_GROUP_LATEST_VIDEOS_NT";
    }
}
