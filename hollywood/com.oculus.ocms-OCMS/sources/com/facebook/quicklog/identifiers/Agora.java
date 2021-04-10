package com.facebook.quicklog.identifiers;

public class Agora {
    public static final short MODULE_ID = 453;
    public static final int SURFACE_PAGING_TTRC = 29687810;
    public static final int SURFACE_TTRC = 29687809;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "AGORA_SURFACE_PAGING_TTRC" : "AGORA_SURFACE_TTRC";
    }
}
