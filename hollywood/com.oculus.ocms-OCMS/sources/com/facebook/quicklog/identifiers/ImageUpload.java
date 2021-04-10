package com.facebook.quicklog.identifiers;

public class ImageUpload {
    public static final int ANDROID_IMAGE_UPLOAD = 49807361;
    public static final short MODULE_ID = 760;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IMAGE_UPLOAD_ANDROID_IMAGE_UPLOAD";
    }
}
