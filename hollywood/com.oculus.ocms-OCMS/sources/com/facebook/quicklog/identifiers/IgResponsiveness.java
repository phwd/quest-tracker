package com.facebook.quicklog.identifiers;

public class IgResponsiveness {
    public static final short MODULE_ID = 877;
    public static final int TOUCH_STALL = 57475073;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_RESPONSIVENESS_TOUCH_STALL";
    }
}
