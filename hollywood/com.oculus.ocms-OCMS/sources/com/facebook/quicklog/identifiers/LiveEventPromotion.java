package com.facebook.quicklog.identifiers;

public class LiveEventPromotion {
    public static final int LIVE_VIDEO_EVENT_PROMOTION = 237963161;
    public static final short MODULE_ID = 3631;

    public static String getMarkerName(int i) {
        return i != 1945 ? "UNDEFINED_QPL_EVENT" : "LIVE_EVENT_PROMOTION_LIVE_VIDEO_EVENT_PROMOTION";
    }
}
