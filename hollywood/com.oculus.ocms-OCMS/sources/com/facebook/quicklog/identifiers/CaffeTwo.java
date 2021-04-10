package com.facebook.quicklog.identifiers;

public class CaffeTwo {
    public static final short MODULE_ID = 231;
    public static final int NET_DELAY = 15138817;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "CAFFE_TWO_NET_DELAY";
    }
}
