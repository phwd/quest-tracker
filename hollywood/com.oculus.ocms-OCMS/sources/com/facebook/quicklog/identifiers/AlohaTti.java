package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class AlohaTti {
    public static final int ABILITY_AUTHORIZATION_LOADING = 19660840;
    public static final int AFTER_2FA_ENTERED_LOADING = 19660837;
    public static final int AFTER_CONFIRMATION_CODE_ENTERED_LOADING = 19660833;
    public static final int AFTER_FB_CREDENTIALS_ENTERED_LOADING = 19660835;
    public static final int APP_STORE_OPEN = 19660851;
    public static final int ASSISTANT_RESPONSE_LATENCY = 19660808;
    public static final int BIOMETRIC_SYSTEM_RESPONSE_TIME = 19660852;
    public static final int CALL_ACCEPT_INCOMING_CALL_FROM_UI = 19660809;
    public static final int CALL_INCOMING_CALL_START = 19660813;
    public static final int CALL_MERGE_CONFLICTING_CALL_FROM_UI = 19660812;
    public static final int CALL_OPEN_CALL_UI_FROM_CONTACT_CARD = 19660807;
    public static final int CALL_REJECT_INCOMING_CALL_FROM_UI = 19660810;
    public static final int CALL_SWITCH_CONFLICTING_CALL_FROM_UI = 19660811;
    public static final int CONTACTS_APP_OPEN = 19660844;
    public static final int CONTACT_CARD_OPEN = 19660843;
    public static final int CONTACT_TAB_LOAD = 19660845;
    public static final int DOWNLOAD_OTA_TOUR_PACKAGE = 19660806;
    public static final int EFFECT_LOAD_TO_RENDER_DELAY = 19660826;
    public static final int EFFECT_SELECT_TO_RENDER_DELAY = 19660819;
    public static final int EFFECT_SET_TO_LOAD_DELAY = 19660825;
    public static final int FACEBOOK_ADD_ACCOUNT_SETUP = 19660857;
    public static final int FACEBOOK_LOGIN_SETUP = 19660855;
    public static final int FETCH_SUPERFRAME_CONFIG = 19660824;
    public static final int FETCH_SUPERFRAME_PLACEHOLDERS = 19660823;
    public static final int INITIAL_WIFI_SCAN_LOADING = 19660828;
    public static final int LOAD_CONTACTS_FROM_SUPERFRAME = 19660801;
    public static final int LOAD_FIRSTCARD_ON_SUPERFRAME = 19660802;
    public static final int LOAD_OA_ENROLLMENT = 19665954;
    public static final int LOAD_SUGGESTED_CONTACTS_LAUNCHER = 19660841;
    public static final short MODULE_ID = 300;
    public static final int OOBE_START_TO_FINISH = 19660822;
    public static final int OTA_CHECK_LOADING = 19660832;
    public static final int REMOTE_CONNECTING = 19660849;
    public static final int REMOTE_CONNECTION_OVERALL = 19660846;
    public static final int REMOTE_FALLBACK_CONNECTION_OVERALL = 19660847;
    public static final int REMOTE_FOUND = 19660848;
    public static final int RICO_DEVICE_TOKEN_LOADING = 19660839;
    public static final int RICO_PAIRING_CODE_LOADING = 19660838;
    public static final int SAVE_NAME_LOADING = 19660829;
    public static final int START_ALOHA_SETTINGS_APP = 19660820;
    public static final int STORYTELLING_INITIAL_LOAD = 19660815;
    public static final int STORYTELLING_PAGE_TO_PAGE = 19660816;
    public static final int STORYTELLING_PRELOADING_DELAY = 19660817;
    public static final int STORYTELLING_RENDER_TO_SEEN = 19660853;
    public static final int STORYTELLING_TRAY_LOAD = 19668065;
    public static final int WHATSAPP_ADD_ACCOUNT_SETUP = 19660858;
    public static final int WHATSAPP_LOGIN_SETUP = 19660854;
    public static final int WIFI_CONNECTION_LOADING = 19660834;
    public static final int WIFI_CONNECTION_TIME_ALOHA = 19660814;
    public static final int WORKPLACE_LOGIN_SETUP = 19660856;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "ALOHA_TTI_LOAD_CONTACTS_FROM_SUPERFRAME";
        }
        if (i == 2) {
            return "ALOHA_TTI_LOAD_FIRSTCARD_ON_SUPERFRAME";
        }
        if (i == 19) {
            return "ALOHA_TTI_EFFECT_SELECT_TO_RENDER_DELAY";
        }
        if (i == 20) {
            return "ALOHA_TTI_START_ALOHA_SETTINGS_APP";
        }
        if (i == 28) {
            return "ALOHA_TTI_INITIAL_WIFI_SCAN_LOADING";
        }
        if (i == 29) {
            return "ALOHA_TTI_SAVE_NAME_LOADING";
        }
        if (i == 5154) {
            return "ALOHA_TTI_LOAD_OA_ENROLLMENT";
        }
        if (i == 7265) {
            return "ALOHA_TTI_STORYTELLING_TRAY_LOAD";
        }
        switch (i) {
            case 6:
                return "ALOHA_TTI_DOWNLOAD_OTA_TOUR_PACKAGE";
            case 7:
                return "ALOHA_TTI_CALL_OPEN_CALL_UI_FROM_CONTACT_CARD";
            case 8:
                return "ALOHA_TTI_ASSISTANT_RESPONSE_LATENCY";
            case 9:
                return "ALOHA_TTI_CALL_ACCEPT_INCOMING_CALL_FROM_UI";
            case 10:
                return "ALOHA_TTI_CALL_REJECT_INCOMING_CALL_FROM_UI";
            case 11:
                return "ALOHA_TTI_CALL_SWITCH_CONFLICTING_CALL_FROM_UI";
            case 12:
                return "ALOHA_TTI_CALL_MERGE_CONFLICTING_CALL_FROM_UI";
            case 13:
                return "ALOHA_TTI_CALL_INCOMING_CALL_START";
            case 14:
                return "ALOHA_TTI_WIFI_CONNECTION_TIME_ALOHA";
            case 15:
                return "ALOHA_TTI_STORYTELLING_INITIAL_LOAD";
            case 16:
                return "ALOHA_TTI_STORYTELLING_PAGE_TO_PAGE";
            case 17:
                return "ALOHA_TTI_STORYTELLING_PRELOADING_DELAY";
            default:
                switch (i) {
                    case 22:
                        return "ALOHA_TTI_OOBE_START_TO_FINISH";
                    case 23:
                        return "ALOHA_TTI_FETCH_SUPERFRAME_PLACEHOLDERS";
                    case 24:
                        return "ALOHA_TTI_FETCH_SUPERFRAME_CONFIG";
                    case 25:
                        return "ALOHA_TTI_EFFECT_SET_TO_LOAD_DELAY";
                    case 26:
                        return "ALOHA_TTI_EFFECT_LOAD_TO_RENDER_DELAY";
                    default:
                        switch (i) {
                            case 32:
                                return "ALOHA_TTI_OTA_CHECK_LOADING";
                            case 33:
                                return "ALOHA_TTI_AFTER_CONFIRMATION_CODE_ENTERED_LOADING";
                            case 34:
                                return "ALOHA_TTI_WIFI_CONNECTION_LOADING";
                            case 35:
                                return "ALOHA_TTI_AFTER_FB_CREDENTIALS_ENTERED_LOADING";
                            default:
                                switch (i) {
                                    case 37:
                                        return "ALOHA_TTI_AFTER_2FA_ENTERED_LOADING";
                                    case 38:
                                        return "ALOHA_TTI_RICO_PAIRING_CODE_LOADING";
                                    case 39:
                                        return "ALOHA_TTI_RICO_DEVICE_TOKEN_LOADING";
                                    case 40:
                                        return "ALOHA_TTI_ABILITY_AUTHORIZATION_LOADING";
                                    case 41:
                                        return "ALOHA_TTI_LOAD_SUGGESTED_CONTACTS_LAUNCHER";
                                    default:
                                        switch (i) {
                                            case 43:
                                                return "ALOHA_TTI_CONTACT_CARD_OPEN";
                                            case 44:
                                                return "ALOHA_TTI_CONTACTS_APP_OPEN";
                                            case 45:
                                                return "ALOHA_TTI_CONTACT_TAB_LOAD";
                                            case 46:
                                                return "ALOHA_TTI_REMOTE_CONNECTION_OVERALL";
                                            case 47:
                                                return "ALOHA_TTI_REMOTE_FALLBACK_CONNECTION_OVERALL";
                                            case 48:
                                                return "ALOHA_TTI_REMOTE_FOUND";
                                            case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                                return "ALOHA_TTI_REMOTE_CONNECTING";
                                            default:
                                                switch (i) {
                                                    case 51:
                                                        return "ALOHA_TTI_APP_STORE_OPEN";
                                                    case 52:
                                                        return "Biometric System Response Time";
                                                    case 53:
                                                        return "ALOHA_TTI_STORYTELLING_RENDER_TO_SEEN";
                                                    case 54:
                                                        return "ALOHA_TTI_WHATSAPP_LOGIN_SETUP";
                                                    case 55:
                                                        return "ALOHA_TTI_FACEBOOK_LOGIN_SETUP";
                                                    case 56:
                                                        return "ALOHA_TTI_WORKPLACE_LOGIN_SETUP";
                                                    case 57:
                                                        return "ALOHA_TTI_FACEBOOK_ADD_ACCOUNT_SETUP";
                                                    case 58:
                                                        return "ALOHA_TTI_WHATSAPP_ADD_ACCOUNT_SETUP";
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
