package com.facebook.quicklog.identifiers;

public class Accessibility {
    public static final short MODULE_ID = 219;
    public static final int TOUCH_EXPLORATION_GESTURE_START = 14352385;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ACCESSIBILITY_TOUCH_EXPLORATION_GESTURE_START";
    }
}
