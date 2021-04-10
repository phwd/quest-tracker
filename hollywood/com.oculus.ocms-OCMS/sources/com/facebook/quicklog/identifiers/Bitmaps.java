package com.facebook.quicklog.identifiers;

public class Bitmaps {
    public static final short MODULE_ID = 13;
    public static final int THUMBNAIL_MAKER = 851969;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "BITMAPS_THUMBNAIL_MAKER";
    }
}
