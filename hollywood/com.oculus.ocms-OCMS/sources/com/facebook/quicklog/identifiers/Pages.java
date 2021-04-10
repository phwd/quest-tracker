package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class Pages {
    public static final int ADMIN_SURFACE_DYNAMIC_TABS_TTI = 1245342;
    public static final int ADMIN_SURFACE_TTI = 1245332;
    public static final int APP_ONCREATE = 1245279;
    public static final int AUTH_TO_LOGIN_COMPLETE = 1245281;
    public static final int CHROME_CREATE = 1245206;
    public static final int CHROME_RESUME = 1245207;
    public static final int COLD_START_FROM_DEEP_LINKING = 1245280;
    public static final int COLD_START_TAG = 1245277;
    public static final int COLD_START_TO_LOGIN_SCREEN = 1245278;
    public static final int COLD_START_TO_PAGE_VIEW_CREATED = 1245209;
    public static final int COMMSHUB_CONVERSATION_LOAD = 1245323;
    public static final int COMMSHUB_MESSAGE_LIST_LOAD_FIRST_PAGE = 1245321;
    public static final int COMMSHUB_MESSAGE_LIST_LOAD_NEXT_PAGE = 1245322;
    public static final int COMMSHUB_MESSAGE_LIST_RELOAD_PAGE = 1245324;
    public static final int COMMSHUB_MESSAGE_LIST_UPDATE_FILTER = 1245325;
    public static final int COMMSHUB_SAVED_REPLIES_LOAD = 1245326;
    public static final int CREATE_THREAD_LIST_METRIC_NAME = 1245301;
    public static final int CROWDSOURCING_GRAPH_EDITOR_LOAD = 1245329;
    public static final int CROWDSOURCING_GRAPH_EDITOR_MAP_LOAD = 1245344;
    public static final int CS_POST_SELECTOR_TTI = 1245331;
    public static final int CS_PROMOTIONS_HUB_TTI = 1245330;
    public static final int CS_SEE_ALL_PROMOTIONS_TTI = 1245340;
    public static final int DESERIALIZE_ALL_PAGES = 1245205;
    public static final int DESERIALIZE_AND_COMPILE_URI_CONFIG = 1245211;
    public static final int DESERIALIZE_PAGE_ATTRIBUTES = 1245204;
    public static final int EVENT_PROFILE_PLUS_QP_NEWSFEED_AP_TRANSITION_FUNNEL = 1257982;
    public static final int FACEWEB_CREATE_TAG = 1245282;
    public static final int FB4A_ADMINED_FIRST_POSTS_BY_OTHERS_STORIES = 1245185;
    public static final int FB4A_ADMINED_FIRST_STORIES = 1245193;
    public static final int FB4A_PAGE_FIRST_POSTS_BY_OTHERS_STORIES = 1245195;
    public static final int FB4A_PAGE_FIRST_STORIES_SEQUENCE = 1245192;
    public static final int LOGIN_TO_ALL_PAGES_LOADED_CHROME = 1245210;
    public static final short MODULE_ID = 19;
    public static final int NT_PAGE_INFO = 1245349;
    public static final int PAGES_ANDROID_ADMIN_TTRC = 1245355;
    public static final int PAGES_ANDROID_PAGINATION_TAILLOAD = 1245354;
    public static final int PAGES_ANDROID_TTRC = 1245343;
    public static final int PAGES_LAUNCHPOINT_SCROLL_LOAD = 1245316;
    public static final int PAGES_LAUNCHPOINT_TTI = 1245315;
    public static final int PAGES_MANAGER_COLD_START = 1245196;
    public static final int PAGES_MANAGER_COLD_START_FIRST_RUN = 1245197;
    public static final int PAGES_MANAGER_FIRST_POSTS_BY_OTHERS_STORIES = 1245186;
    public static final int PAGES_MANAGER_FIRST_STORIES = 1245194;
    public static final int PAGES_MANAGER_WARM_START = 1245198;
    public static final int PAGES_MOBILECONFIG_INIT_IOS = 1245338;
    public static final int PAGES_PROFILE_PIC_LOAD = 1245285;
    public static final int PAGES_REACTION_TAB_LOADING = 1245339;
    public static final int PAGES_SURFACE_HEADER_LOAD = 1245334;
    public static final int PAGES_TAB_TTRC = 1245353;
    public static final int PAGES_TIMELINE_DRAW_FIRST_CARD = 1245231;
    public static final int PAGE_EARLY_FETCHER_PREPARE = 1245327;
    public static final int PAGE_HEADER_FETCH = 1245306;
    public static final int PAGE_HEADER_LOAD = 1245312;
    public static final int PAGE_SURFACE_BATCH_CARD_DATA_LOADED = 1245328;
    public static final int PAGE_SURFACE_FIRST_CARD = 1245309;
    public static final int PAGE_TAB_LOADING_TTI_ANDROID = 1245337;
    public static final int PAGE_TIME_TO_PRIMARY = 1245229;
    public static final int PAGE_TIME_TO_SECONDARY = 1245230;
    public static final int RN_PROMOTIONS_HUB_TTI = 1245351;
    public static final int SERVICE_LEAD_GEN_MOBILE = 1245333;
    public static final int WARM_START_TO_PAGE_VIEW_CREATED = 1245208;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "PAGES_FB4A_ADMINED_FIRST_POSTS_BY_OTHERS_STORIES";
        }
        if (i == 2) {
            return "PAGES_PAGES_MANAGER_FIRST_POSTS_BY_OTHERS_STORIES";
        }
        if (i == 131) {
            return "PAGES_PAGES_LAUNCHPOINT_TTI";
        }
        if (i == 132) {
            return "PAGES_PAGES_LAUNCHPOINT_SCROLL_LOAD";
        }
        switch (i) {
            case 8:
                return "PAGES_FB4A_PAGE_FIRST_STORIES_SEQUENCE";
            case 9:
                return "PAGES_FB4A_ADMINED_FIRST_STORIES";
            case 10:
                return "PAGES_PAGES_MANAGER_FIRST_STORIES";
            case 11:
                return "PAGES_FB4A_PAGE_FIRST_POSTS_BY_OTHERS_STORIES";
            case 12:
                return "PAGES_PAGES_MANAGER_COLD_START";
            case 13:
                return "PAGES_PAGES_MANAGER_COLD_START_FIRST_RUN";
            case 14:
                return "PAGES_PAGES_MANAGER_WARM_START";
            default:
                switch (i) {
                    case 20:
                        return "PAGES_DESERIALIZE_PAGE_ATTRIBUTES";
                    case 21:
                        return "PAGES_DESERIALIZE_ALL_PAGES";
                    case 22:
                        return "PAGES_CHROME_CREATE";
                    case 23:
                        return "PAGES_CHROME_RESUME";
                    case 24:
                        return "PAGES_WARM_START_TO_PAGE_VIEW_CREATED";
                    case 25:
                        return "PAGES_COLD_START_TO_PAGE_VIEW_CREATED";
                    case 26:
                        return "PAGES_LOGIN_TO_ALL_PAGES_LOADED_CHROME";
                    case 27:
                        return "PAGES_DESERIALIZE_AND_COMPILE_URI_CONFIG";
                    default:
                        switch (i) {
                            case 45:
                                return "PAGES_PAGE_TIME_TO_PRIMARY";
                            case 46:
                                return "PAGES_PAGE_TIME_TO_SECONDARY";
                            case 47:
                                return "PAGES_PAGES_TIMELINE_DRAW_FIRST_CARD";
                            default:
                                switch (i) {
                                    case 93:
                                        return "PAGES_COLD_START_TAG";
                                    case 94:
                                        return "PAGES_COLD_START_TO_LOGIN_SCREEN";
                                    case 95:
                                        return "PAGES_APP_ONCREATE";
                                    case 96:
                                        return "PAGES_COLD_START_FROM_DEEP_LINKING";
                                    case 97:
                                        return "PAGES_AUTH_TO_LOGIN_COMPLETE";
                                    case 98:
                                        return "PAGES_FACEWEB_CREATE_TAG";
                                    default:
                                        switch (i) {
                                            case 101:
                                                return "PAGES_PAGES_PROFILE_PIC_LOAD";
                                            case UL.id._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID /*{ENCODED_INT: 117}*/:
                                                return "PAGES_CREATE_THREAD_LIST_METRIC_NAME";
                                            case 122:
                                                return "PAGES_PAGE_HEADER_FETCH";
                                            case 125:
                                                return "PAGES_PAGE_SURFACE_FIRST_CARD";
                                            case 128:
                                                return "PAGES_PAGE_HEADER_LOAD";
                                            case 137:
                                                return "PAGES_COMMSHUB_MESSAGE_LIST_LOAD_FIRST_PAGE";
                                            case 138:
                                                return "PAGES_COMMSHUB_MESSAGE_LIST_LOAD_NEXT_PAGE";
                                            case 139:
                                                return "PAGES_COMMSHUB_CONVERSATION_LOAD";
                                            case 140:
                                                return "PAGES_COMMSHUB_MESSAGE_LIST_RELOAD_PAGE";
                                            case 141:
                                                return "PAGES_COMMSHUB_MESSAGE_LIST_UPDATE_FILTER";
                                            case 142:
                                                return "PAGES_COMMSHUB_SAVED_REPLIES_LOAD";
                                            case 143:
                                                return "PAGES_PAGE_EARLY_FETCHER_PREPARE";
                                            case 144:
                                                return "PAGES_PAGE_SURFACE_BATCH_CARD_DATA_LOADED";
                                            case 145:
                                                return "PAGES_CROWDSOURCING_GRAPH_EDITOR_LOAD";
                                            case 146:
                                                return "PAGES_CS_PROMOTIONS_HUB_TTI";
                                            case 147:
                                                return "PAGES_CS_POST_SELECTOR_TTI";
                                            case 148:
                                                return "PAGES_ADMIN_SURFACE_TTI";
                                            case 149:
                                                return "PAGES_SERVICE_LEAD_GEN_MOBILE";
                                            case 150:
                                                return "PAGES_PAGES_SURFACE_HEADER_LOAD";
                                            case 165:
                                                return "PAGES_NT_PAGE_INFO";
                                            case 167:
                                                return "PAGES_RN_PROMOTIONS_HUB_TTI";
                                            case 12798:
                                                return "PAGES_EVENT_PROFILE_PLUS_QP_NEWSFEED_AP_TRANSITION_FUNNEL";
                                            default:
                                                switch (i) {
                                                    case 153:
                                                        return "PAGES_PAGE_TAB_LOADING_TTI_ANDROID";
                                                    case 154:
                                                        return "PAGES_PAGES_MOBILECONFIG_INIT_IOS";
                                                    case 155:
                                                        return "PAGES_PAGES_REACTION_TAB_LOADING";
                                                    case 156:
                                                        return "PAGES_CS_SEE_ALL_PROMOTIONS_TTI";
                                                    default:
                                                        switch (i) {
                                                            case 158:
                                                                return "PAGES_ADMIN_SURFACE_DYNAMIC_TABS_TTI";
                                                            case 159:
                                                                return "PAGES_PAGES_ANDROID_TTRC";
                                                            case 160:
                                                                return "PAGES_CROWDSOURCING_GRAPH_EDITOR_MAP_LOAD";
                                                            default:
                                                                switch (i) {
                                                                    case 169:
                                                                        return "PAGES_PAGES_TAB_TTRC";
                                                                    case 170:
                                                                        return "PAGES_PAGES_ANDROID_PAGINATION_TAILLOAD";
                                                                    case 171:
                                                                        return "PAGES_PAGES_ANDROID_ADMIN_TTRC";
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
}
