package com.facebook.quicklog.identifiers;

public class IgFeedSharingProfile {
    public static final int CAMERA_INIT = 18284547;
    public static final int DISPLAY_SIMILAR_ACCOUNTS = 18284552;
    public static final int GALLERY_LAUNCH = 18284545;
    public static final int GALLERY_PREVIEW_TTI = 18284546;
    public static final int HIGHLIGHTS_TRAY_LOAD = 18284551;
    public static final short MODULE_ID = 279;
    public static final int PROFILE_PAGE_LOAD_TTI = 18284548;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 7 ? i != 8 ? "UNDEFINED_QPL_EVENT" : "IG_FEED_SHARING_PROFILE_DISPLAY_SIMILAR_ACCOUNTS" : "IG_FEED_SHARING_PROFILE_HIGHLIGHTS_TRAY_LOAD" : "IG_FEED_SHARING_PROFILE_PROFILE_PAGE_LOAD_TTI" : "IG_FEED_SHARING_PROFILE_CAMERA_INIT" : "IG_FEED_SHARING_PROFILE_GALLERY_PREVIEW_TTI" : "IG_FEED_SHARING_PROFILE_GALLERY_LAUNCH";
    }
}
