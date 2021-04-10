package com.facebook.quicklog.identifiers;

public class InteractiveMedia {
    public static final int CARD_FOCUSED_VISIBLE_EVENT = 900281134;
    public static final int FEED_UNIT_USER_FLOW = 900276267;
    public static final int IMAGE_DOWNLOAD_EVENT = 900271649;
    public static final short MODULE_ID = 13737;

    public static String getMarkerName(int i) {
        return i != 3617 ? i != 8235 ? i != 13102 ? "UNDEFINED_QPL_EVENT" : "INTERACTIVE_MEDIA_CARD_FOCUSED_VISIBLE_EVENT" : "INTERACTIVE_MEDIA_FEED_UNIT_USER_FLOW" : "INTERACTIVE_MEDIA_IMAGE_DOWNLOAD_EVENT";
    }
}
