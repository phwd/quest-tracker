package com.facebook.quicklog.identifiers;

public class IgNavigationLatency {
    public static final int ACCOUNT_SWITCH = 31784965;
    public static final int CLIPS_VIEWER_OPEN = 31795699;
    public static final int COMMENT_THREAD = 31784962;
    public static final int DIRECT_INBOX = 31784971;
    public static final int DIRECT_THREAD = 31784972;
    public static final int EXPLORE_CHAINING = 31784993;
    public static final int EXPLORE_POPULAR = 31784973;
    public static final int FEED_GALLERY = 31784964;
    public static final int FEED_GALLERY_CAMERA = 31784976;
    public static final int IGTV_DESTINATION_SAVED = 31790574;
    public static final int IGTV_DISCOVER = 31784995;
    public static final int IGTV_GALLERY = 31784990;
    public static final int IGTV_HASHTAG = 31795886;
    public static final int IGTV_HOME = 31784996;
    public static final int IGTV_PROFILE = 31785000;
    public static final int IGTV_SAVED = 31785001;
    public static final int IGTV_TV_GUIDE = 31784998;
    public static final int IGTV_VIEWER = 31784999;
    public static final int IGTV_WATCH_HISTORY = 31792011;
    public static final int IG_PROFILE = 31784979;
    public static final int IG_REELS_AUDIO_PAGE = 31795876;
    public static final int IG_REELS_EFFECT_PAGE = 31792025;
    public static final int IG_REELS_HOME = 31800314;
    public static final int IG_REELS_VIEWER = 31790861;
    public static final int LOCATION_MAIN_FEED = 31784961;
    public static final int LOCATION_PICKER = 31789744;
    public static final short MODULE_ID = 485;
    public static final int NEWSFEED_YOU = 31784970;
    public static final int REELS_VIEWER_PAGING = 31784994;
    public static final int REEL_COMPOSER_CAMERA = 31784974;
    public static final int SAVED_MEDIA = 31785002;
    public static final int SHOPPING_HOME = 31784989;
    public static final int SHOPPING_HOME_SUBDESTINATION = 31791198;
    public static final int STORIES_HOME = 31793242;
    public static final int STORY_GALLERY = 31799936;
    public static final int STORY_GIF_STICKERS = 31790760;
    public static final int STORY_POSTCAPTURE = 31791240;
    public static final int STORY_SHARE_SHEET = 31790220;
    public static final int STORY_STICKERS = 31798883;
    public static final int STORY_VIEWER = 31784991;
    public static final int URI_HANDLER = 31784977;
    public static final int USER_LIST_FOLLOWERS = 31784968;
    public static final int USER_LIST_FOLLOWING = 31784969;
    public static final int USER_LIST_FOLLOW_REQUESTS = 31784967;
    public static final int USER_LIST_LIKERS = 31784966;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "IG_NAVIGATION_LATENCY_LOCATION_MAIN_FEED";
        }
        if (i == 2) {
            return "IG_NAVIGATION_LATENCY_COMMENT_THREAD";
        }
        if (i == 16) {
            return "IG_NAVIGATION_LATENCY_FEED_GALLERY_CAMERA";
        }
        if (i == 17) {
            return "IG_NAVIGATION_LATENCY_URI_HANDLER";
        }
        switch (i) {
            case 4:
                return "IG_NAVIGATION_LATENCY_FEED_GALLERY";
            case 5:
                return "IG_NAVIGATION_LATENCY_ACCOUNT_SWITCH";
            case 6:
                return "IG_NAVIGATION_LATENCY_USER_LIST_LIKERS";
            case 7:
                return "IG_NAVIGATION_LATENCY_USER_LIST_FOLLOW_REQUESTS";
            case 8:
                return "IG_NAVIGATION_LATENCY_USER_LIST_FOLLOWERS";
            case 9:
                return "IG_NAVIGATION_LATENCY_USER_LIST_FOLLOWING";
            case 10:
                return "IG_NAVIGATION_LATENCY_NEWSFEED_YOU";
            case 11:
                return "IG_NAVIGATION_LATENCY_DIRECT_INBOX";
            case 12:
                return "IG_NAVIGATION_LATENCY_DIRECT_THREAD";
            case 13:
                return "IG_NAVIGATION_LATENCY_EXPLORE_POPULAR";
            case 14:
                return "IG_NAVIGATION_LATENCY_REEL_COMPOSER_CAMERA";
            default:
                switch (i) {
                    case 19:
                        return "IG_NAVIGATION_LATENCY_IG_PROFILE";
                    case 4784:
                        return "IG_NAVIGATION_LATENCY_LOCATION_PICKER";
                    case 5260:
                        return "IG_NAVIGATION_LATENCY_STORY_SHARE_SHEET";
                    case 5614:
                        return "IG_NAVIGATION_LATENCY_IGTV_DESTINATION_SAVED";
                    case 5800:
                        return "IG_NAVIGATION_LATENCY_STORY_GIF_STICKERS";
                    case 5901:
                        return "IG_NAVIGATION_LATENCY_IG_REELS_VIEWER";
                    case 6238:
                        return "IG_NAVIGATION_LATENCY_SHOPPING_HOME_SUBDESTINATION";
                    case 6280:
                        return "IG_NAVIGATION_LATENCY_STORY_POSTCAPTURE";
                    case 7051:
                        return "IG_NAVIGATION_LATENCY_IGTV_WATCH_HISTORY";
                    case 7065:
                        return "IG_NAVIGATION_LATENCY_IG_REELS_EFFECT_PAGE";
                    case 8282:
                        return "IG_NAVIGATION_LATENCY_STORIES_HOME";
                    case 10739:
                        return "IG_NAVIGATION_LATENCY_CLIPS_VIEWER_OPEN";
                    case 10916:
                        return "IG_NAVIGATION_LATENCY_IG_REELS_AUDIO_PAGE";
                    case 10926:
                        return "IG_NAVIGATION_LATENCY_IGTV_HASHTAG";
                    case 13923:
                        return "IG_NAVIGATION_LATENCY_STORY_STICKERS";
                    case 14976:
                        return "IG_NAVIGATION_LATENCY_STORY_GALLERY";
                    case 15354:
                        return "IG_NAVIGATION_LATENCY_IG_REELS_HOME";
                    default:
                        switch (i) {
                            case 29:
                                return "IG_NAVIGATION_LATENCY_SHOPPING_HOME";
                            case 30:
                                return "IG_NAVIGATION_LATENCY_IGTV_GALLERY";
                            case 31:
                                return "IG_NAVIGATION_LATENCY_STORY_VIEWER";
                            default:
                                switch (i) {
                                    case 33:
                                        return "IG_NAVIGATION_LATENCY_EXPLORE_CHAINING";
                                    case 34:
                                        return "IG_NAVIGATION_LATENCY_REELS_VIEWER_PAGING";
                                    case 35:
                                        return "IG_NAVIGATION_LATENCY_IGTV_DISCOVER";
                                    case 36:
                                        return "IG_NAVIGATION_LATENCY_IGTV_HOME";
                                    default:
                                        switch (i) {
                                            case 38:
                                                return "IG_NAVIGATION_LATENCY_IGTV_TV_GUIDE";
                                            case 39:
                                                return "IG_NAVIGATION_LATENCY_IGTV_VIEWER";
                                            case 40:
                                                return "IG_NAVIGATION_LATENCY_IGTV_PROFILE";
                                            case 41:
                                                return "IG_NAVIGATION_LATENCY_IGTV_SAVED";
                                            case 42:
                                                return "IG_NAVIGATION_LATENCY_SAVED_MEDIA";
                                            default:
                                                return "UNDEFINED_QPL_EVENT";
                                        }
                                }
                        }
                }
        }
    }
}
