package com.facebook.quicklog.identifiers;

public class FeedCameraUsage {
    public static final short MODULE_ID = 835;
    public static final int STITCH = 54722561;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FEED_CAMERA_USAGE_STITCH";
    }
}
