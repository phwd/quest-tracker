package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class Video {
    public static final int ABR_DECISION = 1900580;
    public static final int ANDROID_GROOT_COMPONENT_EVENT = 1910742;
    public static final int ANDROID_VIDEO_BLACKSCREEN_EVENT = 1900627;
    public static final int ANDROID_VIDEO_STATE_LIFECYCLE = 1910579;
    public static final int BIND_FEED_VIDEO = 1900602;
    public static final int BIND_PLAYER_SERVICE = 1900587;
    public static final int CHANNEL_FEED_UP_NEXT_TTI_ANDROID_VIDEO = 1900601;
    public static final int CHANNEL_TRANSITION = 1900558;
    public static final int CHANNEL_VIDEO_PLAYER = 1900546;
    public static final int DISMISS_VIDEO = 1900567;
    public static final int ENTER_AD_BREAK_RESPONSIVENESS = 1900605;
    public static final int EXOPLAYER_DO_SOME_WORK = 1900563;
    public static final int FBGROOT_PLAYER_STATE = 1900625;
    public static final int FS_SESSION_STEADY_STATE = 1913576;
    public static final int FULLSCREEN_CHANNEL_LIVE_TRANSITION = 1900616;
    public static final int FULLSCREEN_LIVE_TRANSITION = 1900614;
    public static final int FULLSCREEN_PLAYER_ENTER_RESPONSIVENESS = 1900619;
    public static final int FULLSCREEN_PLAYER_USER_ACTION_RESPONSIVENESS = 1900620;
    public static final int FULLSCREEN_TRANSITION = 1900547;
    public static final int GROOT_DECISION_EVENT = 1900612;
    public static final int GROOT_FS_DECISION_EVENT = 1900626;
    public static final int GROOT_RELIABILITY_EVENT = 1908377;
    public static final int GROOT_VIDEO_TRANSITION = 1900609;
    public static final int HERO_LIVE_MODULE_DOWNLOAD = 1900600;
    public static final int HERO_MODULE_DOWNLOAD = 1900593;
    public static final int HERO_MODULE_INIT = 1900592;
    public static final int HERO_PLAYER_STATE = 1900624;
    public static final int HTTP_TRANSFER_EVENT = 1900576;
    public static final int INITIALIZATION = 1900545;
    public static final int JEWEL_NOTIFICATION_TO_PLAYBACK = 1900571;
    public static final int LIVE_FULLSCREEN_ENTER_30S_RESPONSIVENESS = 1900615;
    public static final short MODULE_ID = 29;
    public static final int MOUNT_FEED_VIDEO = 1900579;
    public static final int MOUNT_GROOT_VIDEO_COMPONENT = 1900608;
    public static final int PARSE_SEGMENT_TIMELINE_ANDROID = 1900599;
    public static final int PLAYER_ACTION = 1900588;
    public static final int PLAYER_BACK_BTN_PRESSED = 1900568;
    public static final int PLAYER_INIT = 1900586;
    public static final int PLUGIN_CONTAINER_MOUNT = 1912199;
    public static final int PREFETCH_ITEM = 1900548;
    public static final int PREPARE_FEED_VIDEO = 1900572;
    public static final int QUALITY_SUMMARY_EVENT = 1900561;
    public static final int RICH_VIDEO_PLAYER_EVENTS = 1900570;
    public static final int RICH_VIDEO_PLAYER_EVENTS_WATCH_FEED = 1900581;
    public static final int RVP_PLAYBACK_REASON = 1910422;
    public static final int RVP_PLUGIN_LOAD = 1900594;
    public static final int SCURBBER = 1900597;
    public static final int SOCIAL_PLAYER_TRANSITION = 1900566;
    public static final int START_AD_BREAK_ANDROID = 1900603;
    public static final int STORY_VIDEO_PROFILER_START = 1900598;
    public static final int THUMBNAIL = 1900591;
    public static final int VIDEO_COMPONENT_STATE = 1900623;
    public static final int VIDEO_FETCH_REQUEST_EVENT = 1900590;
    public static final int VIDEO_HOME_FUNNEL = 1900554;
    public static final int VIDEO_HOME_LOADING = 1900553;
    public static final int VIDEO_PLAYBACK_EVENT = 1900557;
    public static final int VIDEO_PLAYBACK_STALL = 1900562;
    public static final int VIDEO_PLAYBACK_STALL_DATA = 1900589;
    public static final int VIDEO_PLAYBACK_STATE = 1900578;
    public static final int VIDEO_PROFILER_START = 1900596;
    public static final int VIDEO_QUALITIES = 1900582;
    public static final int VIDEO_SOUND_BTN_STATE = 1914800;
    public static final int VIDEO_UNUSED = 1900559;
    public static final int VIDEO_UNUSED_II = 1900560;
    public static final int WARION_ARLTW_TTI = 1900611;
    public static final int WATCH_ARLTW_RELATED_VIDEO_SCROLL_PERF = 1900613;
    public static final int WATCH_TAB_E2E_TTI = 1900577;
    public static final int WATCH_TAB_SCROLL_PERF = 1900583;
    public static final int WWW_TEST_EVENT_FOR_CONNOR = 1917715;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "VIDEO_INITIALIZATION";
        }
        if (i == 2) {
            return "VIDEO_CHANNEL_VIDEO_PLAYER";
        }
        if (i == 3) {
            return "VIDEO_FULLSCREEN_TRANSITION";
        }
        if (i == 4) {
            return "VIDEO_PREFETCH_ITEM";
        }
        if (i == 9) {
            return "VIDEO_VIDEO_HOME_LOADING";
        }
        if (i == 10) {
            return "VIDEO_VIDEO_HOME_FUNNEL";
        }
        if (i == 64) {
            return "VIDEO_MOUNT_GROOT_VIDEO_COMPONENT";
        }
        if (i == 65) {
            return "VIDEO_GROOT_VIDEO_TRANSITION";
        }
        switch (i) {
            case 13:
                return "VIDEO_VIDEO_PLAYBACK_EVENT";
            case 14:
                return "VIDEO_CHANNEL_TRANSITION";
            case 15:
                return "VIDEO_VIDEO_UNUSED";
            case 16:
                return "VIDEO_VIDEO_UNUSED_II";
            case 17:
                return "VIDEO_QUALITY_SUMMARY_EVENT";
            case 18:
                return "VIDEO_VIDEO_PLAYBACK_STALL";
            case 19:
                return "VIDEO_EXOPLAYER_DO_SOME_WORK";
            default:
                switch (i) {
                    case 22:
                        return "VIDEO_SOCIAL_PLAYER_TRANSITION";
                    case 23:
                        return "VIDEO_DISMISS_VIDEO";
                    case 24:
                        return "VIDEO_PLAYER_BACK_BTN_PRESSED";
                    default:
                        switch (i) {
                            case 26:
                                return "VIDEO_RICH_VIDEO_PLAYER_EVENTS";
                            case 27:
                                return "VIDEO_JEWEL_NOTIFICATION_TO_PLAYBACK";
                            case 28:
                                return "VIDEO_PREPARE_FEED_VIDEO";
                            default:
                                switch (i) {
                                    case 32:
                                        return "VIDEO_HTTP_TRANSFER_EVENT";
                                    case 33:
                                        return "VIDEO_WATCH_TAB_E2E_TTI";
                                    case 34:
                                        return "VIDEO_VIDEO_PLAYBACK_STATE";
                                    case 35:
                                        return "VIDEO_MOUNT_FEED_VIDEO";
                                    case 36:
                                        return "VIDEO_ABR_DECISION";
                                    case 37:
                                        return "VIDEO_RICH_VIDEO_PLAYER_EVENTS_WATCH_FEED";
                                    case 38:
                                        return "VIDEO_VIDEO_QUALITIES";
                                    case 39:
                                        return "VIDEO_WATCH_TAB_SCROLL_PERF";
                                    default:
                                        switch (i) {
                                            case 42:
                                                return "VIDEO_PLAYER_INIT";
                                            case 43:
                                                return "VIDEO_BIND_PLAYER_SERVICE";
                                            case 44:
                                                return "VIDEO_PLAYER_ACTION";
                                            case 45:
                                                return "VIDEO_VIDEO_PLAYBACK_STALL_DATA";
                                            case 46:
                                                return "VIDEO_VIDEO_FETCH_REQUEST_EVENT";
                                            case 47:
                                                return "VIDEO_THUMBNAIL";
                                            case 48:
                                                return "VIDEO_HERO_MODULE_INIT";
                                            case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                                return "VIDEO_HERO_MODULE_DOWNLOAD";
                                            case 50:
                                                return "VIDEO_RVP_PLUGIN_LOAD";
                                            case UL.id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                                                return "Enter in-stream ads responsiveness measuring";
                                            case 75:
                                                return "VIDEO_FULLSCREEN_PLAYER_ENTER_RESPONSIVENESS";
                                            case 76:
                                                return "VIDEO_FULLSCREEN_PLAYER_USER_ACTION_RESPONSIVENESS";
                                            case 79:
                                                return "VIDEO_VIDEO_COMPONENT_STATE";
                                            case 80:
                                                return "VIDEO_HERO_PLAYER_STATE";
                                            case 81:
                                                return "VIDEO_FBGROOT_PLAYER_STATE";
                                            case 82:
                                                return "VIDEO_GROOT_FS_DECISION_EVENT";
                                            case 83:
                                                return "VIDEO_ANDROID_VIDEO_BLACKSCREEN_EVENT";
                                            case 7833:
                                                return "VIDEO_GROOT_RELIABILITY_EVENT";
                                            case 9878:
                                                return "VIDEO_RVP_PLAYBACK_REASON";
                                            case 10035:
                                                return "VIDEO_ANDROID_VIDEO_STATE_LIFECYCLE";
                                            case 10198:
                                                return "VIDEO_ANDROID_GROOT_COMPONENT_EVENT";
                                            case 11655:
                                                return "VIDEO_PLUGIN_CONTAINER_MOUNT";
                                            case 13032:
                                                return "VIDEO_FS_SESSION_STEADY_STATE";
                                            case 14256:
                                                return "VIDEO_VIDEO_SOUND_BTN_STATE";
                                            case 17171:
                                                return "VIDEO_WWW_TEST_EVENT_FOR_CONNOR";
                                            default:
                                                switch (i) {
                                                    case 52:
                                                        return "VIDEO_VIDEO_PROFILER_START";
                                                    case 53:
                                                        return "VIDEO_SCURBBER";
                                                    case 54:
                                                        return "VIDEO_STORY_VIDEO_PROFILER_START";
                                                    case 55:
                                                        return "VIDEO_PARSE_SEGMENT_TIMELINE_ANDROID";
                                                    case 56:
                                                        return "VIDEO_HERO_LIVE_MODULE_DOWNLOAD";
                                                    case 57:
                                                        return "VIDEO_CHANNEL_FEED_UP_NEXT_TTI_ANDROID_VIDEO";
                                                    case 58:
                                                        return "VIDEO_BIND_FEED_VIDEO";
                                                    case 59:
                                                        return "VIDEO_START_AD_BREAK_ANDROID";
                                                    default:
                                                        switch (i) {
                                                            case 67:
                                                                return "VIDEO_WARION_ARLTW_TTI";
                                                            case 68:
                                                                return "VIDEO_GROOT_DECISION_EVENT";
                                                            case 69:
                                                                return "VIDEO_WATCH_ARLTW_RELATED_VIDEO_SCROLL_PERF";
                                                            case 70:
                                                                return "VIDEO_FULLSCREEN_LIVE_TRANSITION";
                                                            case 71:
                                                                return "VIDEO_LIVE_FULLSCREEN_ENTER_30S_RESPONSIVENESS";
                                                            case 72:
                                                                return "VIDEO_FULLSCREEN_CHANNEL_LIVE_TRANSITION";
                                                            default:
                                                                return "UNDEFINED_QPL_EVENT";
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
