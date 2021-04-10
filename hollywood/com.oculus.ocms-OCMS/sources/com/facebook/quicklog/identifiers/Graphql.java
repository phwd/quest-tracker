package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class Graphql {
    public static final int AB_VISITOR_APPLY = 3211319;
    public static final int BENCHMARK_READ_CONSISTENT_FIELDS = 3211300;
    public static final int BENCHMARK_READ_CONSISTENT_FIELDS_FLATBUFFER = 3211301;
    public static final int BENCHMARK_RMT_TOGGLE_LIKE = 3211293;
    public static final int CACHEPOLICY_CACHE_ONLY_POLICY = 3211274;
    public static final int CACHEPOLICY_FETCH_AND_FILL = 3211273;
    public static final int CACHEPOLICY_NETWORK_ONLY_POLICY = 3211272;
    public static final int CONSISTENCY_BENCHMARK_DISK_READ = 3211291;
    public static final int CONSISTENCY_BENCHMARK_UPDATE_FROM_CACHE = 3211292;
    public static final int CONSISTENCY_MODEL_UPDATER = 3211267;
    public static final int CONSISTENT_FIELDS_QUERY = 3211310;
    public static final int CONSISTENT_FIELDS_TUPLE_QUERY = 3211320;
    public static final int FETCH_FROM_DB = 3211294;
    public static final int FETCH_MSG_FAIL = 3211285;
    public static final int GRAPHQL_BATCH_QUERY = 3211303;
    public static final int GRAPHQL_MUTATION = 3211305;
    public static final int GRAPHQL_READ_QUERY = 3211302;
    public static final int GRAPHSERVICE_CONSISTENCY_CONFIRMED = 3211316;
    public static final int GRAPHSERVICE_CONSISTENCY_EVENT = 3211334;
    public static final int GRAPHSERVICE_CONSISTENCY_OPTIMISTIC_CANCEL = 3211318;
    public static final int GRAPHSERVICE_CONSISTENCY_OPTIMISTIC_START = 3211317;
    public static final int GRAPHSERVICE_CONSISTENCY_UPDATE = 3211330;
    public static final int GRAPHSERVICE_LOAD_NEXT_PAGE = 3211326;
    public static final int GRAPHSERVICE_LOAD_PAGE = 3211323;
    public static final int GRAPHSERVICE_LOAD_PREVIOUS_PAGE = 3211327;
    public static final int GRAPHSERVICE_MUTATION = 3211325;
    public static final int GRAPHSERVICE_NODESOURCE_WRITE = 3215871;
    public static final int GRAPHSERVICE_QUERY_EXECUTION = 3211329;
    public static final int GRAPHSERVICE_QUERY_EXECUTION_EVENT = 3211333;
    public static final int GRAPHSERVICE_QUERY_EXECUTOR = 3211322;
    public static final int GRAPHSERVICE_READ_QUERY = 3211321;
    public static final int GRAPHSERVICE_RESET_PAGE = 3211328;
    public static final int GRAPHSERVICE_TREE_BUILDER = 3222028;
    public static final int IS_DEEP_EQUAL_GUESS = 3211339;
    public static final int IS_DEEP_EQUAL_GUESS_CORRECT = 3211335;
    public static final int IS_DEEP_EQUAL_GUESS_FALSE_NEGATIVE = 3211337;
    public static final int IS_DEEP_EQUAL_GUESS_FALSE_POSITIVE = 3211336;
    public static final int IS_DEEP_EQUAL_GUESS_UNKNOWN = 3211338;
    public static final int LIVE_QUERY_CANCEL = 3211313;
    public static final int LIVE_QUERY_ERROR = 3211315;
    public static final int LIVE_QUERY_RECEIVE = 3211314;
    public static final int LIVE_QUERY_RETRY = 3211312;
    public static final int LIVE_QUERY_SUBSCRIBE = 3211311;
    public static final short MODULE_ID = 49;
    public static final int OPTIMISTIC_MUTATION = 3216712;
    public static final int PERF_EVENT_CACHE_HIT = 3211280;
    public static final int PERF_EVENT_CACHE_MISS = 3211281;
    public static final int PERF_MARKER_GET_1000_FRIENDS = 3211279;
    public static final int PERF_MARKER_GET_100_MAPS = 3211287;
    public static final int PERF_MARKER_MUTATION = 3211276;
    public static final int PERF_MARKER_PUT_1000_FRIENDS = 3211278;
    public static final int PERF_MARKER_SUBSCRIPTION_PUSH = 3211277;
    public static final int TRIM_ON_BACKGROUND = 3211307;
    public static final int TRIM_TO_MINIMUM = 3211265;
    public static final int TRIM_TO_NOTHING = 3211266;
    public static final int UPDATE_DB = 3211299;
    public static final int UPDATE_MODEL_FROM_CACHE = 3211295;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "GRAPHQL_TRIM_TO_MINIMUM";
        }
        if (i == 2) {
            return "GRAPHQL_TRIM_TO_NOTHING";
        }
        if (i == 3) {
            return "GRAPHQL_CONSISTENCY_MODEL_UPDATER";
        }
        if (i == 21) {
            return "GRAPHQL_FETCH_MSG_FAIL";
        }
        if (i == 23) {
            return "GRAPHQL_PERF_MARKER_GET_100_MAPS";
        }
        if (i == 41) {
            return "GRAPHQL_GRAPHQL_MUTATION";
        }
        if (i == 43) {
            return "GRAPHQL_TRIM_ON_BACKGROUND";
        }
        if (i == 4607) {
            return "GRAPHQL_GRAPHSERVICE_NODESOURCE_WRITE";
        }
        if (i == 5448) {
            return "GRAPHQL_OPTIMISTIC_MUTATION";
        }
        if (i == 10764) {
            return "GRAPHQL_GRAPHSERVICE_TREE_BUILDER";
        }
        switch (i) {
            case 8:
                return "GRAPHQL_CACHEPOLICY_NETWORK_ONLY_POLICY";
            case 9:
                return "GRAPHQL_CACHEPOLICY_FETCH_AND_FILL";
            case 10:
                return "GRAPHQL_CACHEPOLICY_CACHE_ONLY_POLICY";
            default:
                switch (i) {
                    case 12:
                        return "GRAPHQL_PERF_MARKER_MUTATION";
                    case 13:
                        return "GRAPHQL_PERF_MARKER_SUBSCRIPTION_PUSH";
                    case 14:
                        return "GRAPHQL_PERF_MARKER_PUT_1000_FRIENDS";
                    case 15:
                        return "GRAPHQL_PERF_MARKER_GET_1000_FRIENDS";
                    case 16:
                        return "GRAPHQL_PERF_EVENT_CACHE_HIT";
                    case 17:
                        return "GRAPHQL_PERF_EVENT_CACHE_MISS";
                    default:
                        switch (i) {
                            case 27:
                                return "GRAPHQL_CONSISTENCY_BENCHMARK_DISK_READ";
                            case 28:
                                return "GRAPHQL_CONSISTENCY_BENCHMARK_UPDATE_FROM_CACHE";
                            case 29:
                                return "GRAPHQL_BENCHMARK_RMT_TOGGLE_LIKE";
                            case 30:
                                return "GRAPHQL_FETCH_FROM_DB";
                            case 31:
                                return "GRAPHQL_UPDATE_MODEL_FROM_CACHE";
                            default:
                                switch (i) {
                                    case 35:
                                        return "GRAPHQL_UPDATE_DB";
                                    case 36:
                                        return "GRAPHQL_BENCHMARK_READ_CONSISTENT_FIELDS";
                                    case 37:
                                        return "GRAPHQL_BENCHMARK_READ_CONSISTENT_FIELDS_FLATBUFFER";
                                    case 38:
                                        return "GRAPHQL_GRAPHQL_READ_QUERY";
                                    case 39:
                                        return "GRAPHQL_GRAPHQL_BATCH_QUERY";
                                    default:
                                        switch (i) {
                                            case 46:
                                                return "GRAPHQL_CONSISTENT_FIELDS_QUERY";
                                            case 47:
                                                return "GRAPHQL_LIVE_QUERY_SUBSCRIBE";
                                            case 48:
                                                return "GRAPHQL_LIVE_QUERY_RETRY";
                                            case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                                return "GRAPHQL_LIVE_QUERY_CANCEL";
                                            case 50:
                                                return "GRAPHQL_LIVE_QUERY_RECEIVE";
                                            case 51:
                                                return "GRAPHQL_LIVE_QUERY_ERROR";
                                            case 52:
                                                return "GRAPHQL_GRAPHSERVICE_CONSISTENCY_CONFIRMED";
                                            case 53:
                                                return "GRAPHQL_GRAPHSERVICE_CONSISTENCY_OPTIMISTIC_START";
                                            case 54:
                                                return "GRAPHQL_GRAPHSERVICE_CONSISTENCY_OPTIMISTIC_CANCEL";
                                            case 55:
                                                return "GRAPHQL_AB_VISITOR_APPLY";
                                            case 56:
                                                return "GRAPHQL_CONSISTENT_FIELDS_TUPLE_QUERY";
                                            case 57:
                                                return "GRAPHQL_GRAPHSERVICE_READ_QUERY";
                                            case 58:
                                                return "GRAPHQL_GRAPHSERVICE_QUERY_EXECUTOR";
                                            case 59:
                                                return "GRAPHQL_GRAPHSERVICE_LOAD_PAGE";
                                            default:
                                                switch (i) {
                                                    case UL.id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                                                        return "GRAPHQL_GRAPHSERVICE_MUTATION";
                                                    case 62:
                                                        return "GRAPHQL_GRAPHSERVICE_LOAD_NEXT_PAGE";
                                                    case 63:
                                                        return "GRAPHQL_GRAPHSERVICE_LOAD_PREVIOUS_PAGE";
                                                    case 64:
                                                        return "GRAPHQL_GRAPHSERVICE_RESET_PAGE";
                                                    case 65:
                                                        return "GRAPHQL_GRAPHSERVICE_QUERY_EXECUTION";
                                                    case 66:
                                                        return "GRAPHQL_GRAPHSERVICE_CONSISTENCY_UPDATE";
                                                    default:
                                                        switch (i) {
                                                            case 69:
                                                                return "GRAPHQL_GRAPHSERVICE_QUERY_EXECUTION_EVENT";
                                                            case 70:
                                                                return "GRAPHQL_GRAPHSERVICE_CONSISTENCY_EVENT";
                                                            case 71:
                                                                return "GRAPHQL_IS_DEEP_EQUAL_GUESS_CORRECT";
                                                            case 72:
                                                                return "GRAPHQL_IS_DEEP_EQUAL_GUESS_FALSE_POSITIVE";
                                                            case 73:
                                                                return "GRAPHQL_IS_DEEP_EQUAL_GUESS_FALSE_NEGATIVE";
                                                            case 74:
                                                                return "GRAPHQL_IS_DEEP_EQUAL_GUESS_UNKNOWN";
                                                            case 75:
                                                                return "GRAPHQL_IS_DEEP_EQUAL_GUESS";
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
