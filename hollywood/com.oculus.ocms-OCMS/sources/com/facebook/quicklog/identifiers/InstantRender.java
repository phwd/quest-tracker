package com.facebook.quicklog.identifiers;

public class InstantRender {
    public static final int CREATE_LAYOUT = 22937601;
    public static final short MODULE_ID = 350;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "INSTANT_RENDER_CREATE_LAYOUT";
    }
}
