package com.facebook.quicklog.identifiers;

public class RtcCoexUserInteraction {
    public static final int EFFECT_BUTTON_TO_EFFECT_TRAY = 51970049;
    public static final int EFFECT_DESELECTION_TO_EFFECT_REMOVED = 51970053;
    public static final int EFFECT_DESELECTION_TO_EFFECT_UI_UPDATE = 51970052;
    public static final int EFFECT_SELECTION_TO_EFFECT_APPLIED_DOWNLOADED = 51970054;
    public static final int EFFECT_SELECTION_TO_EFFECT_APPLIED_NOT_DOWNLOADED = 51970055;
    public static final int EFFECT_SELECTION_TO_EFFECT_UI_UPDATE = 51970050;
    public static final int EFFECT_TRAY_TO_CALL_CONTROLS = 51970051;
    public static final short MODULE_ID = 793;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "RTC_COEX_USER_INTERACTION_EFFECT_BUTTON_TO_EFFECT_TRAY";
            case 2:
                return "RTC_COEX_USER_INTERACTION_EFFECT_SELECTION_TO_EFFECT_UI_UPDATE";
            case 3:
                return "RTC_COEX_USER_INTERACTION_EFFECT_TRAY_TO_CALL_CONTROLS";
            case 4:
                return "RTC_COEX_USER_INTERACTION_EFFECT_DESELECTION_TO_EFFECT_UI_UPDATE";
            case 5:
                return "RTC_COEX_USER_INTERACTION_EFFECT_DESELECTION_TO_EFFECT_REMOVED";
            case 6:
                return "RTC_COEX_USER_INTERACTION_EFFECT_SELECTION_TO_EFFECT_APPLIED_DOWNLOADED";
            case 7:
                return "RTC_COEX_USER_INTERACTION_EFFECT_SELECTION_TO_EFFECT_APPLIED_NOT_DOWNLOADED";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
