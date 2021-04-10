package com.facebook.quicklog.identifiers;

public class CreatorStudioAndroid {
    public static final int COLD_START = 52363265;
    public static final int COMPOSER_EDIT_THUMBNAIL_GENERATION = 52363268;
    public static final int COMPOSER_EDIT_THUMBNAIL_UPLOAD = 52363269;
    public static final int FACEWEB_CREATE = 52363266;
    public static final int MEDIA_PICKER_VIDEO_SELECTION_WAIT_TIME = 52363270;
    public static final short MODULE_ID = 799;
    public static final int PUBLISH_WAIT_TIME = 52363267;
    public static final int SCREEN_TTRC = 52374751;

    public static String getMarkerName(int i) {
        if (i == 11487) {
            return "CREATOR_STUDIO_ANDROID_SCREEN_TTRC";
        }
        switch (i) {
            case 1:
                return "CREATOR_STUDIO_ANDROID_COLD_START";
            case 2:
                return "CREATOR_STUDIO_ANDROID_FACEWEB_CREATE";
            case 3:
                return "CREATOR_STUDIO_ANDROID_PUBLISH_WAIT_TIME";
            case 4:
                return "CREATOR_STUDIO_ANDROID_COMPOSER_EDIT_THUMBNAIL_GENERATION";
            case 5:
                return "CREATOR_STUDIO_ANDROID_COMPOSER_EDIT_THUMBNAIL_UPLOAD";
            case 6:
                return "CREATOR_STUDIO_ANDROID_MEDIA_PICKER_VIDEO_SELECTION_WAIT_TIME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
