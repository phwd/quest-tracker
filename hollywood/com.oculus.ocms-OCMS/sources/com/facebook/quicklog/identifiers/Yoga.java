package com.facebook.quicklog.identifiers;

public class Yoga {
    public static final int LAYOUT_CALCULATION = 35323905;
    public static final short MODULE_ID = 539;
    public static final int STYLE_PROPS = 35323906;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "YOGA_STYLE_PROPS" : "YOGA_LAYOUT_CALCULATION";
    }
}
