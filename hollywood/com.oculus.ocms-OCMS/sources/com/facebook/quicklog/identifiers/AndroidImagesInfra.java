package com.facebook.quicklog.identifiers;

public class AndroidImagesInfra {
    public static final int ACTOR_GATEWAY_IMAGE_LOAD = 26738699;
    public static final int CAMERA_ROLL_IMAGE_LOAD = 26738703;
    public static final int EVENT_PERMALINK_IMAGE_LOAD = 26738711;
    public static final int GEMSTONE_MATCHING_HOME_IMAGE_LOAD_ANDROID = 26738709;
    public static final int GEMSTONE_PROFILE_IMAGE_LOAD_ANDROID = 26738708;
    public static final int GROUPS_TAB_IMAGE_LOAD = 26738712;
    public static final int GROUP_IMAGE_LOAD = 26738693;
    public static final int GROUP_INTEREST_WIZARD_IMAGE_LOAD = 26738702;
    public static final int GROUP_MALL_IMAGE_LOAD = 26738713;
    public static final int IMAGE_FETCH = 26738705;
    public static final int MESSENGER_MEDIA_TRAY_LOAD = 26738710;
    public static final int MESSENGER_STORY_PHOTO_LOAD = 26738700;
    public static final int MESSENGER_THREAD_PHOTO_LOAD = 26738701;
    public static final short MODULE_ID = 408;
    public static final int NATIVE_NEWSFEED = 26738690;
    public static final int NATIVE_TEMPLATES_IMAGE_LOAD = 26738698;
    public static final int NEWS_IMAGE_LOAD = 26751122;
    public static final int PAGES_IMAGE_LOAD = 26738694;
    public static final int PHOTOS_FEED_IMAGE_LOAD = 26738707;
    public static final int PROFILE_IMAGE_LOAD = 26738691;
    public static final int SEARCH_IMAGE_LOAD = 26738706;
    public static final int SEARCH_VOYAGER_IMAGE_LOAD = 26738704;
    public static final int STORY_IMAGE_LOAD = 26738692;
    public static final int STORY_VIEWER_IMAGE_LOAD = 26738695;
    public static final int UNKNOWN = 26738689;

    public static String getMarkerName(int i) {
        if (i == 12434) {
            return "ANDROID_IMAGES_INFRA_NEWS_IMAGE_LOAD";
        }
        switch (i) {
            case 1:
                return "ANDROID_IMAGES_INFRA_UNKNOWN";
            case 2:
                return "ANDROID_IMAGES_INFRA_NATIVE_NEWSFEED";
            case 3:
                return "ANDROID_IMAGES_INFRA_PROFILE_IMAGE_LOAD";
            case 4:
                return "ANDROID_IMAGES_INFRA_STORY_IMAGE_LOAD";
            case 5:
                return "ANDROID_IMAGES_INFRA_GROUP_IMAGE_LOAD";
            case 6:
                return "ANDROID_IMAGES_INFRA_PAGES_IMAGE_LOAD";
            case 7:
                return "ANDROID_IMAGES_INFRA_STORY_VIEWER_IMAGE_LOAD";
            default:
                switch (i) {
                    case 10:
                        return "ANDROID_IMAGES_INFRA_NATIVE_TEMPLATES_IMAGE_LOAD";
                    case 11:
                        return "ANDROID_IMAGES_INFRA_ACTOR_GATEWAY_IMAGE_LOAD";
                    case 12:
                        return "ANDROID_IMAGES_INFRA_MESSENGER_STORY_PHOTO_LOAD";
                    case 13:
                        return "ANDROID_IMAGES_INFRA_MESSENGER_THREAD_PHOTO_LOAD";
                    case 14:
                        return "ANDROID_IMAGES_INFRA_GROUP_INTEREST_WIZARD_IMAGE_LOAD";
                    case 15:
                        return "ANDROID_IMAGES_INFRA_CAMERA_ROLL_IMAGE_LOAD";
                    case 16:
                        return "ANDROID_IMAGES_INFRA_SEARCH_VOYAGER_IMAGE_LOAD";
                    case 17:
                        return "ANDROID_IMAGES_INFRA_IMAGE_FETCH";
                    case 18:
                        return "ANDROID_IMAGES_INFRA_SEARCH_IMAGE_LOAD";
                    case 19:
                        return "ANDROID_IMAGES_INFRA_PHOTOS_FEED_IMAGE_LOAD";
                    case 20:
                        return "ANDROID_IMAGES_INFRA_GEMSTONE_PROFILE_IMAGE_LOAD_ANDROID";
                    case 21:
                        return "ANDROID_IMAGES_INFRA_GEMSTONE_MATCHING_HOME_IMAGE_LOAD_ANDROID";
                    case 22:
                        return "ANDROID_IMAGES_INFRA_MESSENGER_MEDIA_TRAY_LOAD";
                    case 23:
                        return "ANDROID_IMAGES_INFRA_EVENT_PERMALINK_IMAGE_LOAD";
                    case 24:
                        return "ANDROID_IMAGES_INFRA_GROUPS_TAB_IMAGE_LOAD";
                    case 25:
                        return "ANDROID_IMAGES_INFRA_GROUP_MALL_IMAGE_LOAD";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
