package com.facebook.quicklog.identifiers;

public class DisturbingMedia {
    public static final int GRAPHIC_PHOTO_SHOWN_NO_WARNING = 6946817;
    public static final int GRAPHIC_PHOTO_SHOWN_WITH_WARNING = 6946818;
    public static final int GRAPHIC_PHOTO_WARNING_DISMISSED = 6946819;
    public static final short MODULE_ID = 106;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "DISTURBING_MEDIA_GRAPHIC_PHOTO_WARNING_DISMISSED" : "DISTURBING_MEDIA_GRAPHIC_PHOTO_SHOWN_WITH_WARNING" : "DISTURBING_MEDIA_GRAPHIC_PHOTO_SHOWN_NO_WARNING";
    }
}
