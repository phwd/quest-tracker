package com.facebook.quicklog.identifiers;

public class ImageFetch {
    public static final short MODULE_ID = 79;
    public static final int TEST = 5177345;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IMAGE_FETCH_TEST";
    }
}
