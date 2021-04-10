package com.facebook.quicklog.identifiers;

public class Notifications {
    public static final int CONVERSATION_HUB_LOAD = 3473448;
    public static final int CREATE_LAUNCH_CONFIG = 3473445;
    public static final int FBLITE_PUSH_NTA = 3473474;
    public static final int MINI_FEED_TTRC = 3485719;
    public static final short MODULE_ID = 53;
    public static final int MQTT_WAKE_UP = 3473430;
    public static final int NOTIFICATIONS_BADGE_COUNT_CHANGED = 3473467;
    public static final int NOTIFICATIONS_LOAD_ATTEMPT = 3473466;
    public static final int NOTIFICATIONS_MUTATION_ATTEMPT = 3473469;
    public static final int NOTIFICATIONS_PTR_TTI = 3473431;
    public static final int NOTIFICATIONS_REALTIME_EVENT_RECEIVED = 3473473;
    public static final int NOTIFICATIONS_RENDERED = 3473455;
    public static final int NOTIFICATIONS_RESPONSE_RECEIVED = 3473460;
    public static final int NOTIFICATIONS_TAB_TTRC = 3473442;
    public static final int NOTIFICATIONS_TAIL_LOAD_TTI = 3473432;
    public static final int NOTIFICATION_HANDLER = 3473429;
    public static final int NOTIFICATION_HEAD_LOAD = 3473433;
    public static final int NOTIFICATION_SYNC_ERROR = 3473427;
    public static final int NOTIFICATION_TAPPED = 3473475;
    public static final int NOTIFICATION_TAPPED_ANDROID = 3473476;
    public static final int NOTIF_DELTA_ONLY_FETCH = 3473446;
    public static final int NOTIF_FULL_FETCH_FROM_SERVER = 3473410;
    public static final int NOTIF_GET_FROM_DISK = 3473409;
    public static final int NOTIF_JSON_DESERIALIZE = 3473414;
    public static final int NOTIF_LIST_LOAD_TIME_COLD = 3473417;
    public static final int NOTIF_LIST_LOAD_TIME_WARM = 3473418;
    public static final int NOTIF_LOCKSCREEN_PERMALINK_LOAD_TIME = 3473415;
    public static final int NOTIF_NEW_FETCH_FROM_SERVER = 3473411;
    public static final int NOTIF_PERMALINK_REFRESH_STORY_TIME = 3473416;
    public static final int NOTIF_SCROLL_LOAD = 3473421;
    public static final int OPEN_NOTIFICATION_TAB_TTI = 3473426;
    public static final int PERMALINK_FROM_PRELOAD_CAROUSEL_LOAD = 3473438;
    public static final int PERMALINK_IN_APP_NOTIFICATION_HEAD_LOAD = 3473435;
    public static final int PERMALINK_NOTIFICATION_HEAD_LOAD = 3473434;
    public static final int PERMALINK_REDIRECT_FALLBACK_URL = 3473428;
    public static final int POLL_NOTIF = 3473412;
    public static final int PREINIT_NOTIFICATIONS_TAB = 3473440;
    public static final int PULL_TO_REFRESH_LOAD_TIME = 3473413;
    public static final int PUSH_NOTIF_RENDER = 3488631;
    public static final int SEE_POST_FROM_HEAD_LOAD = 3473437;
    public static final int SHOW_NOTIFICATION_IN_SYSTEM_TRAY = 3473423;
    public static final int TIME_TILL_BADGE = 3473441;
    public static final int TIME_TILL_TRACE = 3473472;
    public static final int TIME_TILL_TRAY = 3473470;
    public static final int TIME_TO_GET_INTENT_TARGET = 3488075;

    public static String getMarkerName(int i) {
        if (i == 13) {
            return "NOTIFICATIONS_NOTIF_SCROLL_LOAD";
        }
        if (i == 15) {
            return "NOTIFICATIONS_SHOW_NOTIFICATION_IN_SYSTEM_TRAY";
        }
        if (i == 40) {
            return "NOTIFICATIONS_CONVERSATION_HUB_LOAD";
        }
        if (i == 47) {
            return "NOTIFICATIONS_NOTIFICATIONS_RENDERED";
        }
        if (i == 52) {
            return "NOTIFICATIONS_NOTIFICATIONS_RESPONSE_RECEIVED";
        }
        if (i == 12311) {
            return "NOTIFICATIONS_MINI_FEED_TTRC";
        }
        if (i == 14667) {
            return "NOTIFICATIONS_TIME_TO_GET_INTENT_TARGET";
        }
        if (i == 15223) {
            return "NOTIFICATIONS_PUSH_NOTIF_RENDER";
        }
        if (i == 29) {
            return "NOTIFICATIONS_SEE_POST_FROM_HEAD_LOAD";
        }
        if (i == 30) {
            return "NOTIFICATIONS_PERMALINK_FROM_PRELOAD_CAROUSEL_LOAD";
        }
        if (i == 37) {
            return "NOTIFICATIONS_CREATE_LAUNCH_CONFIG";
        }
        if (i == 38) {
            return "NOTIFICATIONS_NOTIF_DELTA_ONLY_FETCH";
        }
        if (i == 58) {
            return "NOTIFICATIONS_NOTIFICATIONS_LOAD_ATTEMPT";
        }
        if (i == 59) {
            return "NOTIFICATIONS_NOTIFICATIONS_BADGE_COUNT_CHANGED";
        }
        if (i == 61) {
            return "NOTIFICATIONS_NOTIFICATIONS_MUTATION_ATTEMPT";
        }
        if (i == 62) {
            return "NOTIFICATIONS_TIME_TILL_TRAY";
        }
        switch (i) {
            case 1:
                return "NOTIFICATIONS_NOTIF_GET_FROM_DISK";
            case 2:
                return "NOTIFICATIONS_NOTIF_FULL_FETCH_FROM_SERVER";
            case 3:
                return "NOTIFICATIONS_NOTIF_NEW_FETCH_FROM_SERVER";
            case 4:
                return "NOTIFICATIONS_POLL_NOTIF";
            case 5:
                return "NOTIFICATIONS_PULL_TO_REFRESH_LOAD_TIME";
            case 6:
                return "NOTIFICATIONS_NOTIF_JSON_DESERIALIZE";
            case 7:
                return "NOTIFICATIONS_NOTIF_LOCKSCREEN_PERMALINK_LOAD_TIME";
            case 8:
                return "NOTIFICATIONS_NOTIF_PERMALINK_REFRESH_STORY_TIME";
            case 9:
                return "NOTIFICATIONS_NOTIF_LIST_LOAD_TIME_COLD";
            case 10:
                return "NOTIFICATIONS_NOTIF_LIST_LOAD_TIME_WARM";
            default:
                switch (i) {
                    case 18:
                        return "NOTIFICATIONS_OPEN_NOTIFICATION_TAB_TTI";
                    case 19:
                        return "NOTIFICATIONS_NOTIFICATION_SYNC_ERROR";
                    case 20:
                        return "NOTIFICATIONS_PERMALINK_REDIRECT_FALLBACK_URL";
                    case 21:
                        return "NOTIFICATIONS_NOTIFICATION_HANDLER";
                    case 22:
                        return "NOTIFICATIONS_MQTT_WAKE_UP";
                    case 23:
                        return "NOTIFICATIONS_NOTIFICATIONS_PTR_TTI";
                    case 24:
                        return "NOTIFICATIONS_NOTIFICATIONS_TAIL_LOAD_TTI";
                    case 25:
                        return "NOTIFICATIONS_NOTIFICATION_HEAD_LOAD";
                    case 26:
                        return "NOTIFICATIONS_PERMALINK_NOTIFICATION_HEAD_LOAD";
                    case 27:
                        return "NOTIFICATIONS_PERMALINK_IN_APP_NOTIFICATION_HEAD_LOAD";
                    default:
                        switch (i) {
                            case 32:
                                return "NOTIFICATIONS_PREINIT_NOTIFICATIONS_TAB";
                            case 33:
                                return "NOTIFICATIONS_TIME_TILL_BADGE";
                            case 34:
                                return "NOTIFICATIONS_NOTIFICATIONS_TAB_TTRC";
                            default:
                                switch (i) {
                                    case 64:
                                        return "NOTIFICATIONS_TIME_TILL_TRACE";
                                    case 65:
                                        return "NOTIFICATIONS_NOTIFICATIONS_REALTIME_EVENT_RECEIVED";
                                    case 66:
                                        return "NOTIFICATIONS_FBLITE_PUSH_NTA";
                                    case 67:
                                        return "NOTIFICATIONS_NOTIFICATION_TAPPED";
                                    case 68:
                                        return "NOTIFICATIONS_NOTIFICATION_TAPPED_ANDROID";
                                    default:
                                        return "UNDEFINED_QPL_EVENT";
                                }
                        }
                }
        }
    }
}
