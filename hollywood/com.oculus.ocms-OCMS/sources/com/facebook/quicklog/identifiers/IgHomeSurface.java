package com.facebook.quicklog.identifiers;

public class IgHomeSurface {
    public static final int ENTER_HOME = 974460948;
    public static final int FEED_REQUEST = 974460658;
    public static final int LIKER_LIST_SEARCH = 974475670;
    public static final short MODULE_ID = 14869;
    public static final int SCROLL_TO_TOP = 974475790;
    public static final int STORIES_MEDIA_REQUEST = 974456648;
    public static final int STORIES_TRAY_REQUEST = 974456048;

    public static String getMarkerName(int i) {
        return i != 1264 ? i != 1864 ? i != 5874 ? i != 6164 ? i != 20886 ? i != 21006 ? "UNDEFINED_QPL_EVENT" : "IG_HOME_SURFACE_SCROLL_TO_TOP" : "IG_HOME_SURFACE_LIKER_LIST_SEARCH" : "IG_HOME_SURFACE_ENTER_HOME" : "IG_HOME_SURFACE_FEED_REQUEST" : "IG_HOME_SURFACE_STORIES_MEDIA_REQUEST" : "IG_HOME_SURFACE_STORIES_TRAY_REQUEST";
    }
}
