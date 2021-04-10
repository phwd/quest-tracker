package com.facebook.quicklog.identifiers;

public class FbliteCdn {
    public static final short MODULE_ID = 783;
    public static final int OKHTTP = 51314689;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FBLITE_CDN_OKHTTP";
    }
}
