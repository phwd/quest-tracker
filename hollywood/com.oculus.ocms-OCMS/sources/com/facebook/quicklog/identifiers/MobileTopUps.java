package com.facebook.quicklog.identifiers;

public class MobileTopUps {
    public static final int CONTACT_PICKER_ANDROID = 28049409;
    public static final short MODULE_ID = 428;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MOBILE_TOP_UPS_CONTACT_PICKER_ANDROID";
    }
}
