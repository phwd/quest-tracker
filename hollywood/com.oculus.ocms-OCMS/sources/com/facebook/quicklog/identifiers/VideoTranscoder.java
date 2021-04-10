package com.facebook.quicklog.identifiers;

public class VideoTranscoder {
    public static final short MODULE_ID = 351;
    public static final int VIDEO_TRANSCODING = 23003137;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "VIDEO_TRANSCODER_VIDEO_TRANSCODING";
    }
}
