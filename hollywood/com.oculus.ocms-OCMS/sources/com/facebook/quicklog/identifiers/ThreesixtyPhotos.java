package com.facebook.quicklog.identifiers;

public class ThreesixtyPhotos {
    public static final int EQUIRECT_TTI = 21757955;
    public static final short MODULE_ID = 332;
    public static final int TILED_CUBEMAP_TTI = 21757953;
    public static final int TILED_CUBEMAP_TTI_STORIES = 21757956;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "THREESIXTY_PHOTOS_TILED_CUBEMAP_TTI_STORIES" : "THREESIXTY_PHOTOS_EQUIRECT_TTI" : "THREESIXTY_PHOTOS_TILED_CUBEMAP_TTI";
    }
}
