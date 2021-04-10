package com.facebook.quicklog.identifiers;

public class AndroidDitto {
    public static final int BACKGROUND_PATCHED = 47457205;
    public static final int GET_PATCH = 47448065;
    public static final int INIT = 47448067;
    public static final short MODULE_ID = 724;
    public static final int PATCH = 47448066;
    public static final int PATCH_VOLTRON = 47463943;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 9141 ? i != 15879 ? "UNDEFINED_QPL_EVENT" : "ANDROID_DITTO_PATCH_VOLTRON" : "ANDROID_DITTO_BACKGROUND_PATCHED" : "ANDROID_DITTO_INIT" : "ANDROID_DITTO_PATCH" : "ANDROID_DITTO_GET_PATCH";
    }
}
