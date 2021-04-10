package com.facebook.quicklog.identifiers;

public class Platform {
    public static final short MODULE_ID = 123;
    public static final int PLATFORM_COMPOSER_ACTIVITY_TTI = 8060935;
    public static final int PLATFORM_COMPOSER_TTI = 8060932;
    public static final int PLATFORM_JSDIALOG_LAUNCH_SEQUENCE = 8060930;
    public static final int PLATFORM_SHARE_INTENT_HANDLER_PHASE = 8060934;
    public static final int PLATFORM_SHARE_TTI = 8060933;
    public static final int SHOWN_NOTIFICATION = 8076996;
    public static final int SHOWN_NOTIFICATION_TEST = 8076353;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 15425 ? i != 16068 ? i != 4 ? i != 5 ? i != 6 ? i != 7 ? "UNDEFINED_QPL_EVENT" : "PLATFORM_COMPOSER_ACTIVITY_TTI" : "PLATFORM_SHARE_INTENT_HANDLER_PHASE" : "PLATFORM_SHARE_TTI" : "PLATFORM_PLATFORM_COMPOSER_TTI" : "PLATFORM_SHOWN_NOTIFICATION" : "PLATFORM_SHOWN_NOTIFICATION_TEST" : "PLATFORM_PLATFORM_JSDIALOG_LAUNCH_SEQUENCE";
    }
}
