package com.facebook.quicklog.identifiers;

public class InstantShopping {
    public static final int CATALOG_LOAD = 6684673;
    public static final short MODULE_ID = 102;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "INSTANT_SHOPPING_CATALOG_LOAD";
    }
}
