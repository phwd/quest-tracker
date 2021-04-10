package com.facebook.quicklog.identifiers;

public class GraphqlAndroidCursor {
    public static final int APPLY_VISITOR = 8716307;
    public static final int APPLY_VISITORS = 8716305;
    public static final int CONNECTION_CONTROLLER_FETCH_EDGES = 8716324;
    public static final int CONNECTION_CONTROLLER_INITIALIZE = 8716323;
    public static final int CURSOR_DB_CTSCAN_LIKE_CONFIRMED = 8716302;
    public static final int CURSOR_DB_CTSCAN_LIKE_OPTIMISTIC = 8716303;
    public static final int CURSOR_DB_DELETE_SESSION = 8716298;
    public static final int CURSOR_DB_PUT_MULTI = 8716297;
    public static final int CURSOR_DB_PUT_MULTI_CONSISTENCY = 8716317;
    public static final int CURSOR_DB_VISIT_DELTA_BUFFER = 8716301;
    public static final int CURSOR_DB_VISIT_REFLATTEN = 8716300;
    public static final int DESERIALIZE_MODEL = 8716327;
    public static final int FLATTEN_TREE_MODELS_IN_CONNECTION_CONTROLLER_ON_GRAPH_SERVICES = 8716326;
    public static final int FLUSH_SESSION = 8716322;
    public static final short MODULE_ID = 133;
    public static final int QUERY = 8716319;
    public static final int QUERY_CHUNKS = 8716313;
    public static final int QUERY_ROWS = 8716314;
    public static final int TRIM_OLD_ROWS_FROM_SESSIONS = 8716310;
    public static final int TRIM_OLD_SESSIONS = 8716309;
    public static final int TRIM_TO_MINIMUM = 8716312;
    public static final int TRIM_TO_NOTHING = 8716316;
    public static final int TRIM_UNUSED_MODEL_FILES = 8716311;
    public static final int VISIT_ROW = 8716304;

    public static String getMarkerName(int i) {
        switch (i) {
            case 9:
                return "GRAPHQL_ANDROID_CURSOR_CURSOR_DB_PUT_MULTI";
            case 10:
                return "GRAPHQL_ANDROID_CURSOR_CURSOR_DB_DELETE_SESSION";
            case 11:
            case 18:
            case 20:
            case 27:
            case 30:
            case 32:
            case 33:
            case 37:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 12:
                return "GRAPHQL_ANDROID_CURSOR_CURSOR_DB_VISIT_REFLATTEN";
            case 13:
                return "GRAPHQL_ANDROID_CURSOR_CURSOR_DB_VISIT_DELTA_BUFFER";
            case 14:
                return "GRAPHQL_ANDROID_CURSOR_CURSOR_DB_CTSCAN_LIKE_CONFIRMED";
            case 15:
                return "GRAPHQL_ANDROID_CURSOR_CURSOR_DB_CTSCAN_LIKE_OPTIMISTIC";
            case 16:
                return "GRAPHQL_ANDROID_CURSOR_VISIT_ROW";
            case 17:
                return "GRAPHQL_ANDROID_CURSOR_APPLY_VISITORS";
            case 19:
                return "GRAPHQL_ANDROID_CURSOR_APPLY_VISITOR";
            case 21:
                return "GRAPHQL_ANDROID_CURSOR_TRIM_OLD_SESSIONS";
            case 22:
                return "GRAPHQL_ANDROID_CURSOR_TRIM_OLD_ROWS_FROM_SESSIONS";
            case 23:
                return "GRAPHQL_ANDROID_CURSOR_TRIM_UNUSED_MODEL_FILES";
            case 24:
                return "GRAPHQL_ANDROID_CURSOR_TRIM_TO_MINIMUM";
            case 25:
                return "GRAPHQL_ANDROID_CURSOR_QUERY_CHUNKS";
            case 26:
                return "GRAPHQL_ANDROID_CURSOR_QUERY_ROWS";
            case 28:
                return "GRAPHQL_ANDROID_CURSOR_TRIM_TO_NOTHING";
            case 29:
                return "GRAPHQL_ANDROID_CURSOR_CURSOR_DB_PUT_MULTI_CONSISTENCY";
            case 31:
                return "GRAPHQL_ANDROID_CURSOR_QUERY";
            case 34:
                return "GRAPHQL_ANDROID_CURSOR_FLUSH_SESSION";
            case 35:
                return "GRAPHQL_ANDROID_CURSOR_CONNECTION_CONTROLLER_INITIALIZE";
            case 36:
                return "GRAPHQL_ANDROID_CURSOR_CONNECTION_CONTROLLER_FETCH_EDGES";
            case 38:
                return "GRAPHQL_ANDROID_CURSOR_FLATTEN_TREE_MODELS_IN_CONNECTION_CONTROLLER_ON_GRAPH_SERVICES";
            case 39:
                return "GRAPHQL_ANDROID_CURSOR_DESERIALIZE_MODEL";
        }
    }
}
