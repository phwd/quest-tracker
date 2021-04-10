package com.facebook.quicklog.identifiers;

public class WearableKeyboard {
    public static final int APP_START = 838076849;
    public static final short MODULE_ID = 12788;
    public static final int SUGGESTION_LATENCY = 838079209;
    public static final int UI_START = 838079790;

    public static String getMarkerName(int i) {
        return i != 2481 ? i != 4841 ? i != 5422 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_KEYBOARD_UI_START" : "WEARABLE_KEYBOARD_SUGGESTION_LATENCY" : "WEARABLE_KEYBOARD_APP_START";
    }
}
