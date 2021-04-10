package com.facebook.quicklog.identifiers;

public class Photos {
    public static final int INITIAL_LOAD_PHOTOS_TTRC_ANDROID = 1310753;
    public static final int LOAD_PHOTOS_FEED = 1310738;
    public static final int LOAD_PHOTO_BY_SWIPING = 1310731;
    public static final int LOAD_PHOTO_GALLERY_FROM_GALLERY_LAUNCHER = 1310728;
    public static final int LOAD_PHOTO_GALLERY_WITH_PHOTO_FROM_GALLERY_LAUNCHER = 1310729;
    public static final int LOAD_PHOTO_GALLERY_WITH_PHOTO_FROM_GALLERY_LAUNCHER_FROM_SOURCE = 1310734;
    public static final int LOAD_SNOWFLAKE_PHOTO_GALLERY_WITH_PHOTO = 1310726;
    public static final int MEDIA_FETCHER = 1310744;
    public static final int MEDIA_GALLERY_TTI = 1310722;
    public static final short MODULE_ID = 20;
    public static final int PANDORA_LOADING = 1310723;
    public static final int PERF_APPLY_TO_FILE = 1310737;
    public static final int PHOTOS_FEED_INIT_TO_ANIM_END = 1310743;
    public static final int PHOTOS_FEED_TTI = 1310740;
    public static final int PHOTO_TO_VIDEO_CONVERSTION_TTI_ANDROID = 1310745;
    public static final int PROGRESS_NOT_SHOWN = 1310742;
    public static final int PROGRESS_SHOWN = 1310741;
    public static final int SIMPLE_PICKER_LAUNCH = 1310724;
    public static final int TIME_TO_DISPLAY_FACE_BOXES_MARKER = 1310735;
    public static final int UPLOAD_SEQUENCE = 1310721;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "PHOTOS_UPLOAD_SEQUENCE";
        }
        if (i == 2) {
            return "PHOTOS_MEDIA_GALLERY_TTI";
        }
        if (i == 3) {
            return "PHOTOS_PANDORA_LOADING";
        }
        if (i == 4) {
            return "PHOTOS_SIMPLE_PICKER_LAUNCH";
        }
        if (i == 6) {
            return "PHOTOS_LOAD_SNOWFLAKE_PHOTO_GALLERY_WITH_PHOTO";
        }
        if (i == 11) {
            return "LoadPhotoBySwiping-MediaGallery";
        }
        if (i == 33) {
            return "PHOTOS_INITIAL_LOAD_PHOTOS_TTRC_ANDROID";
        }
        if (i == 8) {
            return "LoadPhotoGallery-MediaGallery";
        }
        if (i == 9) {
            return "LoadPhotoGalleryWithPhoto-MediaGallery";
        }
        if (i == 14) {
            return "LoadPhotoGalleryWithPhotoFromSource-MediaGallery";
        }
        if (i == 15) {
            return "PHOTOS_TIME_TO_DISPLAY_FACE_BOXES_MARKER";
        }
        if (i == 17) {
            return "PHOTOS_PERF_APPLY_TO_FILE";
        }
        if (i == 18) {
            return "LoadPhotosFeed";
        }
        switch (i) {
            case 20:
                return "PHOTOS_PHOTOS_FEED_TTI";
            case 21:
                return "PHOTOS_PROGRESS_SHOWN";
            case 22:
                return "PHOTOS_PROGRESS_NOT_SHOWN";
            case 23:
                return "PHOTOS_PHOTOS_FEED_INIT_TO_ANIM_END";
            case 24:
                return "PHOTOS_MEDIA_FETCHER";
            case 25:
                return "PHOTOS_PHOTO_TO_VIDEO_CONVERSTION_TTI_ANDROID";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
