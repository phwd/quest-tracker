package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class Composer {
    public static final int ACTION_BUTTON_PRESSED = 917512;
    public static final int COMPOSER_CONTENT_READY_FROM_NEWSFEED = 917543;
    public static final int COMPOSER_CONTENT_READY_FROM_UI = 917547;
    public static final int COMPOSER_CONTENT_READY_TTI_FROM_NEWSFEED = 917542;
    public static final int COMPOSER_CUSTOM_FONT_FETCH = 917545;
    public static final int COMPOSER_DEPENDENCY_INJECTION = 917522;
    public static final int COMPOSER_DRAW_MARKER = 917524;
    public static final int COMPOSER_FRAGMENT_CREATE_VIEW = 917526;
    public static final int COMPOSER_FRAGMENT_ONCREATE = 917520;
    public static final int COMPOSER_FRAGMENT_SETUP = 917525;
    public static final int COMPOSER_INLINE_MEDIA_PICKER_VISIBLE = 917530;
    public static final int COMPOSER_LAUNCH_FROM_NEWSFEED = 917528;
    public static final int COMPOSER_LAUNCH_PHASE = 917521;
    public static final int COMPOSER_LAUNCH_SEQUENCE = 917505;
    public static final int COMPOSER_LAUNCH_TTI = 917551;
    public static final int COMPOSER_LAUNCH_TTI_FROM_UI = 917549;
    public static final int COMPOSER_MEDIA_CURSOR_READY = 917531;
    public static final int COMPOSER_MESSENGER_INBOX_THREADS_READY = 917544;
    public static final int COMPOSER_NEW_MODEL_BUILDER = 917529;
    public static final int COMPOSER_REFERRER_HANDOFF = 917519;
    public static final int COMPOSER_RENDER_MARKER = 917523;
    public static final int CS_ALBUM_LIST_TTI = 917534;
    public static final int FEED_COMPOSER_CONTENT_READY = 917555;
    public static final int GIF_PICKER_LOAD = 923247;
    public static final int GROUPS_CROSS_POSTING = 917536;
    public static final int INLINE_COMPOSER_LAUNCH_TRIGGER = 917533;
    public static final int LIFE_EVENT_FETCH_BIRTHDAY = 917517;
    public static final short MODULE_ID = 14;
    public static final int NT_GIF_PICKER_TTRC = 922793;
    public static final int PERF_ALBUMS_LIST_FETCH = 917515;
    public static final int PHOTO_LOAD = 917506;
    public static final int POST_DRAW = 917511;
    public static final int SELECTED_PRIVACY_AVAILABLE = 917513;
    public static final int STORIES_COMPOSER_CONTENT_READY = 917554;
    public static final int STORIES_COMPOSER_LAUNCH = 917552;
    public static final int STORIES_COMPOSER_LAUNCH_TTI = 917553;
    public static final int SUGGESTIONS_APPEARANCE_LAUNCH = 917507;
    public static final int TEENS_SHARESHEET_FETCH_GROUPS = 917518;
    public static final int TIME_TO_INTERACT_EXTERNAL_SHARE = 917509;
    public static final int TIME_TO_INTERACT_PLATFORM_SHARE = 917510;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "COMPOSER_COMPOSER_LAUNCH_SEQUENCE";
        }
        if (i == 2) {
            return "COMPOSER_PHOTO_LOAD";
        }
        if (i == 3) {
            return "COMPOSER_SUGGESTIONS_APPEARANCE_LAUNCH";
        }
        if (i == 11) {
            return "COMPOSER_PERF_ALBUMS_LIST_FETCH";
        }
        if (i == 32) {
            return "COMPOSER_GROUPS_CROSS_POSTING";
        }
        if (i == 43) {
            return "COMPOSER_COMPOSER_CONTENT_READY_FROM_UI";
        }
        if (i == 45) {
            return "COMPOSER_COMPOSER_LAUNCH_TTI_FROM_UI";
        }
        if (i == 5289) {
            return "COMPOSER_NT_GIF_PICKER_TTRC";
        }
        if (i == 5743) {
            return "COMPOSER_GIF_PICKER_LOAD";
        }
        if (i == 29) {
            return "COMPOSER_INLINE_COMPOSER_LAUNCH_TRIGGER";
        }
        if (i == 30) {
            return "COMPOSER_CS_ALBUM_LIST_TTI";
        }
        switch (i) {
            case 5:
                return "COMPOSER_TIME_TO_INTERACT_EXTERNAL_SHARE";
            case 6:
                return "COMPOSER_TIME_TO_INTERACT_PLATFORM_SHARE";
            case 7:
                return "COMPOSER_POST_DRAW";
            case 8:
                return "COMPOSER_ACTION_BUTTON_PRESSED";
            case 9:
                return "COMPOSER_SELECTED_PRIVACY_AVAILABLE";
            default:
                switch (i) {
                    case 13:
                        return "COMPOSER_LIFE_EVENT_FETCH_BIRTHDAY";
                    case 14:
                        return "COMPOSER_TEENS_SHARESHEET_FETCH_GROUPS";
                    case 15:
                        return "COMPOSER_COMPOSER_REFERRER_HANDOFF";
                    case 16:
                        return "COMPOSER_COMPOSER_FRAGMENT_ONCREATE";
                    case 17:
                        return "COMPOSER_COMPOSER_LAUNCH_PHASE";
                    case 18:
                        return "COMPOSER_COMPOSER_DEPENDENCY_INJECTION";
                    case 19:
                        return "COMPOSER_COMPOSER_RENDER_MARKER";
                    case 20:
                        return "COMPOSER_COMPOSER_DRAW_MARKER";
                    case 21:
                        return "COMPOSER_COMPOSER_FRAGMENT_SETUP";
                    case 22:
                        return "COMPOSER_COMPOSER_FRAGMENT_CREATE_VIEW";
                    default:
                        switch (i) {
                            case 24:
                                return "COMPOSER_COMPOSER_LAUNCH_FROM_NEWSFEED";
                            case 25:
                                return "COMPOSER_COMPOSER_NEW_MODEL_BUILDER";
                            case 26:
                                return "COMPOSER_COMPOSER_INLINE_MEDIA_PICKER_VISIBLE";
                            case 27:
                                return "COMPOSER_COMPOSER_MEDIA_CURSOR_READY";
                            default:
                                switch (i) {
                                    case 38:
                                        return "COMPOSER_COMPOSER_CONTENT_READY_TTI_FROM_NEWSFEED";
                                    case 39:
                                        return "COMPOSER_COMPOSER_CONTENT_READY_FROM_NEWSFEED";
                                    case 40:
                                        return "COMPOSER_COMPOSER_MESSENGER_INBOX_THREADS_READY";
                                    case 41:
                                        return "COMPOSER_COMPOSER_CUSTOM_FONT_FETCH";
                                    default:
                                        switch (i) {
                                            case 47:
                                                return "COMPOSER_COMPOSER_LAUNCH_TTI";
                                            case 48:
                                                return "COMPOSER_STORIES_COMPOSER_LAUNCH";
                                            case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                                return "COMPOSER_STORIES_COMPOSER_LAUNCH_TTI";
                                            case 50:
                                                return "COMPOSER_STORIES_COMPOSER_CONTENT_READY";
                                            case 51:
                                                return "COMPOSER_FEED_COMPOSER_CONTENT_READY";
                                            default:
                                                return "UNDEFINED_QPL_EVENT";
                                        }
                                }
                        }
                }
        }
    }
}
