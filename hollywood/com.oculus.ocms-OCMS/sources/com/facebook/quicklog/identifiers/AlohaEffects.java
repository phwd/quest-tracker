package com.facebook.quicklog.identifiers;

public class AlohaEffects {
    public static final int EFFECTS_TRAY_LOAD = 47185921;
    public static final short MODULE_ID = 720;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ALOHA_EFFECTS_EFFECTS_TRAY_LOAD";
    }
}
