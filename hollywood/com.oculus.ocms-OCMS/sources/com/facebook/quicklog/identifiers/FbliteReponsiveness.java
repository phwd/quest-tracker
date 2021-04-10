package com.facebook.quicklog.identifiers;

public class FbliteReponsiveness {
    public static final int DMG = 39583747;
    public static final int FBLITE_CLIENT_TTRC = 39583746;
    public static final int FBLITE_COMMERCE_TTRC = 39583757;
    public static final int FBLITE_CREATION_TTRC = 39598140;
    public static final int FBLITE_EXTERNAL_LINK_INTERACTIONS = 39583754;
    public static final int FBLITE_MESSAGING_TTRC = 39583749;
    public static final int FBLITE_PAGES_TTRC = 39583755;
    public static final int FBLITE_PROFILE_TTRC = 39583756;
    public static final int FBLITE_SEARCH_TTRC = 39583752;
    public static final int FBLITE_STORIES_TTRC = 39583750;
    public static final int FBLITE_TTRC = 39583745;
    public static final int FBLITE_USER_INTERACTIONS = 39583748;
    public static final int FBLITE_VIDEOS_TTRC = 39583751;
    public static final int IGLITE_CLIENT_INTERACTIONS = 39583758;
    public static final short MODULE_ID = 604;
    public static final int WEBLITE_USER_INTERACTIONS = 39583753;

    public static String getMarkerName(int i) {
        if (i == 14396) {
            return "FBLITE_REPONSIVENESS_FBLITE_CREATION_TTRC";
        }
        switch (i) {
            case 1:
                return "FBLITE_REPONSIVENESS_FBLITE_TTRC";
            case 2:
                return "FBLITE_REPONSIVENESS_FBLITE_CLIENT_TTRC";
            case 3:
                return "FBLITE_REPONSIVENESS_DMG";
            case 4:
                return "FBLITE_REPONSIVENESS_FBLITE_USER_INTERACTIONS";
            case 5:
                return "FBLITE_REPONSIVENESS_FBLITE_MESSAGING_TTRC";
            case 6:
                return "FBLITE_REPONSIVENESS_FBLITE_STORIES_TTRC";
            case 7:
                return "FBLITE_REPONSIVENESS_FBLITE_VIDEOS_TTRC";
            case 8:
                return "FBLITE_REPONSIVENESS_FBLITE_SEARCH_TTRC";
            case 9:
                return "FBLITE_REPONSIVENESS_WEBLITE_USER_INTERACTIONS";
            case 10:
                return "FBLITE_REPONSIVENESS_FBLITE_EXTERNAL_LINK_INTERACTIONS";
            case 11:
                return "FBLITE_REPONSIVENESS_FBLITE_PAGES_TTRC";
            case 12:
                return "FBLITE_REPONSIVENESS_FBLITE_PROFILE_TTRC";
            case 13:
                return "FBLITE_REPONSIVENESS_FBLITE_COMMERCE_TTRC";
            case 14:
                return "FBLITE_REPONSIVENESS_IGLITE_CLIENT_INTERACTIONS";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
