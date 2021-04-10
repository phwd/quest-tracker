package com.facebook.quicklog.identifiers;

public class PlacePicker {
    public static final short MODULE_ID = 12842;
    public static final int PLACE_PICKER_QPL_FUNNEL = 841627612;

    public static String getMarkerName(int i) {
        return i != 14300 ? "UNDEFINED_QPL_EVENT" : "PLACE_PICKER_PLACE_PICKER_QPL_FUNNEL";
    }
}
