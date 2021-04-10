package com.facebook.quicklog.identifiers;

public class AppPerfx {
    public static final int ACTIVITY_FIRST_FRAME = 44826634;
    public static final int ACTIVITY_FOREGROUND = 44826633;
    public static final int DIALOG_FIRST_FRAME = 44826636;
    public static final int FRAGMENT_FIRST_FRAME = 44826637;
    public static final int IN_ANIMATION = 44826626;
    public static final int IN_CONT_UPDATE = 44826638;
    public static final int IN_GLITCHABLE_PLAYBACK = 44826627;
    public static final int IN_SCROLL = 44826625;
    public static final short MODULE_ID = 684;
    public static final int TAB_ACTIVE = 44826632;
    public static final int TAB_FIRST_FRAME = 44826635;
    public static final int TYPING = 44826629;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "APP_PERFX_IN_SCROLL";
        }
        if (i == 2) {
            return "APP_PERFX_IN_ANIMATION";
        }
        if (i == 3) {
            return "APP_PERFX_IN_GLITCHABLE_PLAYBACK";
        }
        if (i == 5) {
            return "APP_PERFX_TYPING";
        }
        switch (i) {
            case 8:
                return "APP_PERFX_TAB_ACTIVE";
            case 9:
                return "APP_PERFX_ACTIVITY_FOREGROUND";
            case 10:
                return "APP_PERFX_ACTIVITY_FIRST_FRAME";
            case 11:
                return "APP_PERFX_TAB_FIRST_FRAME";
            case 12:
                return "APP_PERFX_DIALOG_FIRST_FRAME";
            case 13:
                return "APP_PERFX_FRAGMENT_FIRST_FRAME";
            case 14:
                return "APP_PERFX_IN_CONT_UPDATE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
