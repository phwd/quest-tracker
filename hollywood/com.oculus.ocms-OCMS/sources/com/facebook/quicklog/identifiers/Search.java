package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class Search {
    public static final int CLIENT_INSTANT_SEARCH_ANDROID = 458819;
    public static final int EVENT_PERMALINK_TTRC_ANDROID_FROM_SEARCH = 464702;
    public static final int EVENT_PERMALINK_TTRC_FBLITE_FROM_SEARCH = 468273;
    public static final int FBLITE_SEARCH_SERP_PAGE_1 = 458823;
    public static final int FBLITE_SEARCH_SERP_PAGE_2 = 458824;
    public static final int FETCH_BOOTSTRAP_KEYWORD_QUERY = 458798;
    public static final int GRAPH_SEARCH_LOCAL_SUGGESTIONS_TYPEAHEAD = 458783;
    public static final int GRAPH_SEARCH_REMOTE_ENTITY_SUGGESTIONS_TYPEAHEAD = 458786;
    public static final int GRAPH_SEARCH_REMOTE_KEYWORD_SUGGESTIONS_TYPEAHEAD = 458785;
    public static final int GRAPH_SEARCH_REMOTE_SUGGESTIONS_TYPEAHEAD = 458782;
    public static final int GRAPH_SEARCH_RESULT_FETCH = 458767;
    public static final int GROUP_FEED_TTRC_FBLITE_FROM_SEARCH = 466093;
    public static final int GROUP_MALL_TTRC_ANDROID_FROM_SEARCH = 469226;
    public static final int INTEREST_DEEP_DIVE_PAGINATION = 458833;
    public static final int INTEREST_DEEP_DIVE_TTRC_FBLITE = 458834;
    public static final int KEYWORD_SEARCH_PERFORMANCE = 458763;
    public static final int KEYWORD_SEARCH_RESULT_PAGE_DISLAY_DONE = 458793;
    public static final int KEYWORD_SEARCH_RESULT_PAGE_LOAD = 458764;
    public static final int KEYWORD_SEARCH_RESULT_PAGE_LOAD_MORE = 458765;
    public static final int LITHO_COMPONENT_CREATION_AND_LAYOUT = 458809;
    public static final int MARKETPLACE_FEED_TTRC_FBLITE_FROM_SEARCH = 465382;
    public static final int MEMORIES_TTRC_FBLITE_FROM_SEARCH = 468333;
    public static final short MODULE_ID = 7;
    public static final int NS_TTRC_ANDROID = 458811;
    public static final int NULL_STATE = 458773;
    public static final int PAGES_ANDROID_TTRC_FROM_SEARCH = 472409;
    public static final int PAGES_FEED_TTRC_FBLITE_FROM_SEARCH = 470237;
    public static final int PAGE_SURFACE_INITIAL_LOAD_LATENCY = 458802;
    public static final int PROFILE_INITIAL_LOAD_TTRC_ANDROID_FROM_SEARCH = 472192;
    public static final int PROFILE_LOAD_TTRC_FBLITE_FROM_SEARCH = 464420;
    public static final int RESULTS_NT_BACKGROUND_ENQUEUE_ANDROID = 458813;
    public static final int RESULTS_NT_BG_PARSE_ANDROID = 458800;
    public static final int RESULTS_NT_FG_WAIT_ANDROID = 458801;
    public static final int RESULTS_NT_FINISH_IDLE_ANDROID = 458814;
    public static final int SEARCH_DB_BOOTSTRAP_DELTA_LOAD = 458778;
    public static final int SEARCH_DB_BOOTSTRAP_FETCH = 458775;
    public static final int SEARCH_DB_BOOTSTRAP_INDEX_LOAD = 458776;
    public static final int SEARCH_DB_BOOTSTRAP_LOAD = 458774;
    public static final int SEARCH_DB_BOOTSTRAP_PREFETCH_LOAD = 458777;
    public static final int SEARCH_DB_BOOTSTRAP_PREFETCH_UPDATE = 458830;
    public static final int SEARCH_DB_BOOTSTRAP_RECENT_SEARCH_DELTA = 458779;
    public static final int SEARCH_END_TO_END = 458828;
    public static final int SEARCH_ENTITIES_TIME_TO_DISPLAY_SUGGESTION = 458772;
    public static final int SEARCH_INTEREST_DEEP_DIVE = 458829;
    public static final int SEARCH_SNIPPET_LAYOUT_ANDROID = 458799;
    public static final int SEARCH_TYPEAHEAD = 458768;
    public static final int SEARCH_TYPEAHEAD_ABANDON_EVENT_LOGGING = 466423;
    public static final int SEARCH_VOYAGER_PAGINATION = 458826;
    public static final int SEARCH_VOYAGER_TOPIC_ENDPOINT = 458817;
    public static final int SEARCH_VOYAGER_TOPIC_FEED = 458816;
    public static final int SEARCH_WATERFALL = 458769;
    public static final int SERP_INITIAL_RENDER_BOOST = 458818;
    public static final int SERP_NETWORK = 458796;
    public static final int SERP_PAGINATION_ANDROID = 458807;
    public static final int SERP_PAGINATION_ANDROID_SPLIT = 474089;
    public static final int SERP_POSTPROCESS = 458795;
    public static final int SERP_PREPROCESS = 458794;
    public static final int SERP_SUGGESTIONS_END_TO_END = 458789;
    public static final int SERP_TTI = 458797;
    public static final int SERP_TTRC_ANDROID = 458803;
    public static final int SERP_TTRC_ANDROID_PEOPLE_TAB = 458822;
    public static final int SERP_TTRC_FBLITE = 458827;
    public static final int SIMPLE_SEARCH_LOCAL_SUGGESTIONS_TYPEAHEAD = 458770;
    public static final int SIMPLE_SEARCH_REMOTE_SUGGESTIONS_TYPEAHEAD = 458771;
    public static final int SUGGESTIONS_END_TO_END = 458784;
    public static final int TA_TTRC_ANDROID = 458804;
    public static final int TYPEAHEAD_KEYPRESS_ANDROID = 458815;
    public static final int WATCH_SERP_TTRC = 458831;

    public static String getMarkerName(int i) {
        if (i == 81) {
            return "SEARCH_INTEREST_DEEP_DIVE_PAGINATION";
        }
        if (i == 82) {
            return "SEARCH_INTEREST_DEEP_DIVE_TTRC_FBLITE";
        }
        switch (i) {
            case 11:
                return "SEARCH_KEYWORD_SEARCH_PERFORMANCE";
            case 12:
                return "SEARCH_KEYWORD_SEARCH_RESULT_PAGE_LOAD";
            case 13:
                return "SEARCH_KEYWORD_SEARCH_RESULT_PAGE_LOAD_MORE";
            default:
                switch (i) {
                    case 15:
                        return "SEARCH_GRAPH_SEARCH_RESULT_FETCH";
                    case 16:
                        return "SEARCH_SEARCH_TYPEAHEAD";
                    case 17:
                        return "SEARCH_SEARCH_WATERFALL";
                    case 18:
                        return "SEARCH_SIMPLE_SEARCH_LOCAL_SUGGESTIONS_TYPEAHEAD";
                    case 19:
                        return "SEARCH_SIMPLE_SEARCH_REMOTE_SUGGESTIONS_TYPEAHEAD";
                    case 20:
                        return "SEARCH_SEARCH_ENTITIES_TIME_TO_DISPLAY_SUGGESTION";
                    case 21:
                        return "SEARCH_NULL_STATE";
                    case 22:
                        return "SEARCH_SEARCH_DB_BOOTSTRAP_LOAD";
                    case 23:
                        return "SEARCH_SEARCH_DB_BOOTSTRAP_FETCH";
                    case 24:
                        return "SEARCH_SEARCH_DB_BOOTSTRAP_INDEX_LOAD";
                    case 25:
                        return "SEARCH_SEARCH_DB_BOOTSTRAP_PREFETCH_LOAD";
                    case 26:
                        return "SEARCH_SEARCH_DB_BOOTSTRAP_DELTA_LOAD";
                    case 27:
                        return "SEARCH_SEARCH_DB_BOOTSTRAP_RECENT_SEARCH_DELTA";
                    default:
                        switch (i) {
                            case 30:
                                return "SEARCH_GRAPH_SEARCH_REMOTE_SUGGESTIONS_TYPEAHEAD";
                            case 31:
                                return "SEARCH_GRAPH_SEARCH_LOCAL_SUGGESTIONS_TYPEAHEAD";
                            case 32:
                                return "SEARCH_SUGGESTIONS_END_TO_END";
                            case 33:
                                return "SEARCH_GRAPH_SEARCH_REMOTE_KEYWORD_SUGGESTIONS_TYPEAHEAD";
                            case 34:
                                return "SEARCH_GRAPH_SEARCH_REMOTE_ENTITY_SUGGESTIONS_TYPEAHEAD";
                            default:
                                switch (i) {
                                    case 37:
                                        return "SEARCH_SERP_SUGGESTIONS_END_TO_END";
                                    case 55:
                                        return "SEARCH_SERP_PAGINATION_ANDROID";
                                    case 57:
                                        return "SEARCH_LITHO_COMPONENT_CREATION_AND_LAYOUT";
                                    case 59:
                                        return "SEARCH_NS_TTRC_ANDROID";
                                    case 5668:
                                        return "SEARCH_PROFILE_LOAD_TTRC_FBLITE_FROM_SEARCH";
                                    case 5950:
                                        return "SEARCH_EVENT_PERMALINK_TTRC_ANDROID_FROM_SEARCH";
                                    case 6630:
                                        return "SEARCH_MARKETPLACE_FEED_TTRC_FBLITE_FROM_SEARCH";
                                    case 7341:
                                        return "SEARCH_GROUP_FEED_TTRC_FBLITE_FROM_SEARCH";
                                    case 7671:
                                        return "SEARCH_SEARCH_TYPEAHEAD_ABANDON_EVENT_LOGGING";
                                    case 9521:
                                        return "SEARCH_EVENT_PERMALINK_TTRC_FBLITE_FROM_SEARCH";
                                    case 9581:
                                        return "SEARCH_MEMORIES_TTRC_FBLITE_FROM_SEARCH";
                                    case 10474:
                                        return "SEARCH_GROUP_MALL_TTRC_ANDROID_FROM_SEARCH";
                                    case 11485:
                                        return "SEARCH_PAGES_FEED_TTRC_FBLITE_FROM_SEARCH";
                                    case 13440:
                                        return "SEARCH_PROFILE_INITIAL_LOAD_TTRC_ANDROID_FROM_SEARCH";
                                    case 13657:
                                        return "SEARCH_PAGES_ANDROID_TTRC_FROM_SEARCH";
                                    case 15337:
                                        return "SEARCH_SERP_PAGINATION_ANDROID_SPLIT";
                                    default:
                                        switch (i) {
                                            case 41:
                                                return "SEARCH_KEYWORD_SEARCH_RESULT_PAGE_DISLAY_DONE";
                                            case 42:
                                                return "SEARCH_SERP_PREPROCESS";
                                            case 43:
                                                return "SEARCH_SERP_POSTPROCESS";
                                            case 44:
                                                return "SEARCH_SERP_NETWORK";
                                            case 45:
                                                return "SEARCH_SERP_TTI";
                                            case 46:
                                                return "SEARCH_FETCH_BOOTSTRAP_KEYWORD_QUERY";
                                            case 47:
                                                return "SEARCH_SEARCH_SNIPPET_LAYOUT_ANDROID";
                                            case 48:
                                                return "SEARCH_RESULTS_NT_BG_PARSE_ANDROID";
                                            case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                                return "SEARCH_RESULTS_NT_FG_WAIT_ANDROID";
                                            case 50:
                                                return "SEARCH_PAGE_SURFACE_INITIAL_LOAD_LATENCY";
                                            case 51:
                                                return "SEARCH_SERP_TTRC_ANDROID";
                                            case 52:
                                                return "SEARCH_TA_TTRC_ANDROID";
                                            default:
                                                switch (i) {
                                                    case UL.id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                                                        return "SEARCH_RESULTS_NT_BACKGROUND_ENQUEUE_ANDROID";
                                                    case 62:
                                                        return "SEARCH_RESULTS_NT_FINISH_IDLE_ANDROID";
                                                    case 63:
                                                        return "SEARCH_TYPEAHEAD_KEYPRESS_ANDROID";
                                                    case 64:
                                                        return "SEARCH_SEARCH_VOYAGER_TOPIC_FEED";
                                                    case 65:
                                                        return "SEARCH_SEARCH_VOYAGER_TOPIC_ENDPOINT";
                                                    case 66:
                                                        return "SEARCH_SERP_INITIAL_RENDER_BOOST";
                                                    case 67:
                                                        return "SEARCH_CLIENT_INSTANT_SEARCH_ANDROID";
                                                    default:
                                                        switch (i) {
                                                            case 70:
                                                                return "SEARCH_SERP_TTRC_ANDROID_PEOPLE_TAB";
                                                            case 71:
                                                                return "SEARCH_FBLITE_SEARCH_SERP_PAGE_1";
                                                            case 72:
                                                                return "SEARCH_FBLITE_SEARCH_SERP_PAGE_2";
                                                            default:
                                                                switch (i) {
                                                                    case 74:
                                                                        return "SEARCH_SEARCH_VOYAGER_PAGINATION";
                                                                    case 75:
                                                                        return "SEARCH_SERP_TTRC_FBLITE";
                                                                    case 76:
                                                                        return "SEARCH_SEARCH_END_TO_END";
                                                                    case 77:
                                                                        return "SEARCH_SEARCH_INTEREST_DEEP_DIVE";
                                                                    case 78:
                                                                        return "SEARCH_SEARCH_DB_BOOTSTRAP_PREFETCH_UPDATE";
                                                                    case 79:
                                                                        return "SEARCH_WATCH_SERP_TTRC";
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
