package com.facebook.quicklog.identifiers;

public class MediaGallery {
    public static final int LOAD_PHOTO_BY_SWIPING = 327688;
    public static final int LOAD_PHOTO_GALLERY_FROM_GALLERY_LAUNCHER = 327685;
    public static final int LOAD_PHOTO_GALLERY_WITH_PHOTO_FROM_GALLERY_LAUNCHER = 327686;
    public static final short MODULE_ID = 5;

    public static String getMarkerName(int i) {
        return i != 5 ? i != 6 ? i != 8 ? "UNDEFINED_QPL_EVENT" : "MEDIA_GALLERY_LOAD_PHOTO_BY_SWIPING" : "MEDIA_GALLERY_LOAD_PHOTO_GALLERY_WITH_PHOTO_FROM_GALLERY_LAUNCHER" : "MEDIA_GALLERY_LOAD_PHOTO_GALLERY_FROM_GALLERY_LAUNCHER";
    }
}
