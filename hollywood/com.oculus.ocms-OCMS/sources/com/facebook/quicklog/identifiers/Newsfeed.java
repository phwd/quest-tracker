package com.facebook.quicklog.identifiers;

public class Newsfeed {
    public static final int CONTEXTUAL_CONFIG_QPL = 12582917;
    public static final int DEPRECATED_CODE_VALIDATOR_ANDROID = 12582919;
    public static final int FEED_START_TO_TTI = 12582913;
    public static final short MODULE_ID = 192;
    public static final int NEWSFEED_FAVORITES_TTRC = 12582921;
    public static final int NEWSFEED_MOST_RECENT_TTRC = 12582922;
    public static final int NEWSFEED_SEEN_TTRC = 12582923;
    public static final int NEWSFEED_TTRC = 12582920;
    public static final int SCROLL_STATE_TRACKER_DATA_ANDROID = 12582916;
    public static final int VIEWPOINT_SPONSORED_IMPRESSION_FB4A = 12582918;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "NEWSFEED_FEED_START_TO_TTI";
        }
        switch (i) {
            case 4:
                return "NEWSFEED_SCROLL_STATE_TRACKER_DATA_ANDROID";
            case 5:
                return "NEWSFEED_CONTEXTUAL_CONFIG_QPL";
            case 6:
                return "NEWSFEED_VIEWPOINT_SPONSORED_IMPRESSION_FB4A";
            case 7:
                return "NEWSFEED_DEPRECATED_CODE_VALIDATOR_ANDROID";
            case 8:
                return "NEWSFEED_NEWSFEED_TTRC";
            case 9:
                return "NEWSFEED_NEWSFEED_FAVORITES_TTRC";
            case 10:
                return "NEWSFEED_NEWSFEED_MOST_RECENT_TTRC";
            case 11:
                return "NEWSFEED_NEWSFEED_SEEN_TTRC";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
