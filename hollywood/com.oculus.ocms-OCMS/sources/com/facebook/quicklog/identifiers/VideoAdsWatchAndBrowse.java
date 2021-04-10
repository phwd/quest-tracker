package com.facebook.quicklog.identifiers;

public class VideoAdsWatchAndBrowse {
    public static final short MODULE_ID = 507;
    public static final int USER_CLICK_ANDROID = 33226753;
    public static final int WATCH_AND_BROWSE_LANDING_PAGE_TTI = 33226756;
    public static final int WATCH_AND_BROWSE_RVP_TRANSITION = 33226755;
    public static final int WATCH_AND_BROWSE_TRANSITION = 33226754;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "VIDEO_ADS_WATCH_AND_BROWSE_WATCH_AND_BROWSE_LANDING_PAGE_TTI" : "VIDEO_ADS_WATCH_AND_BROWSE_WATCH_AND_BROWSE_RVP_TRANSITION" : "VIDEO_ADS_WATCH_AND_BROWSE_WATCH_AND_BROWSE_TRANSITION" : "VIDEO_ADS_WATCH_AND_BROWSE_USER_CLICK_ANDROID";
    }
}
