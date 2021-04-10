package com.facebook.quicklog.identifiers;

public class AndroidLocalMedia {
    public static final int CAFFE_TWO_SUPPORTED = 14942209;
    public static final short MODULE_ID = 228;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ANDROID_LOCAL_MEDIA_CAFFE_TWO_SUPPORTED";
    }
}
