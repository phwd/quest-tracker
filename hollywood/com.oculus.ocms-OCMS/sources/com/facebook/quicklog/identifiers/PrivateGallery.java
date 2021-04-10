package com.facebook.quicklog.identifiers;

public class PrivateGallery {
    public static final int GALLERY_TAB_TTI = 21823489;
    public static final short MODULE_ID = 333;
    public static final int PAGINATION_TTI = 21823491;
    public static final int THUMBNAIL_TTI = 21823490;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "PRIVATE_GALLERY_PAGINATION_TTI" : "PRIVATE_GALLERY_THUMBNAIL_TTI" : "PRIVATE_GALLERY_GALLERY_TAB_TTI";
    }
}
