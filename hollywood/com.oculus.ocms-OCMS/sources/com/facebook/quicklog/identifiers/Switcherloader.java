package com.facebook.quicklog.identifiers;

public class Switcherloader {
    public static final short MODULE_ID = 171;
    public static final int SWITCHER_ITEMS_LOAD_TIME = 11206657;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "SWITCHERLOADER_SWITCHER_ITEMS_LOAD_TIME";
    }
}
