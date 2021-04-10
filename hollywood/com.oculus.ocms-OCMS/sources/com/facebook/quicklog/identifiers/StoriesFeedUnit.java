package com.facebook.quicklog.identifiers;

public class StoriesFeedUnit {
    public static final short MODULE_ID = 384;
    public static final int TRAY_LOAD_TTI = 25165827;
    public static final int TRAY_VISIBILITY_CHANGE = 25165831;

    public static String getMarkerName(int i) {
        return i != 3 ? i != 7 ? "UNDEFINED_QPL_EVENT" : "STORIES_FEED_UNIT_TRAY_VISIBILITY_CHANGE" : "STORIES_FEED_UNIT_TRAY_LOAD_TTI";
    }
}
