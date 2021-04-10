package com.facebook.quicklog.identifiers;

public class Siren {
    public static final int APP_TTI = 12713985;
    public static final int LIST_ITEM_RENDER = 12713986;
    public static final short MODULE_ID = 194;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "SIREN_LIST_ITEM_RENDER" : "SIREN_APP_TTI";
    }
}
