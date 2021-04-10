package com.facebook.quicklog.identifiers;

public class AndroidLowStorage {
    public static final short MODULE_ID = 514;
    public static final int SHOW_FG_BSOD = 33685506;
    public static final int SHOW_FG_BSOD_CTA = 33685507;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "ANDROID_LOW_STORAGE_SHOW_FG_BSOD_CTA" : "ANDROID_LOW_STORAGE_SHOW_FG_BSOD";
    }
}
