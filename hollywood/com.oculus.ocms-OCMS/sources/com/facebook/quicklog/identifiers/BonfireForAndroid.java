package com.facebook.quicklog.identifiers;

public class BonfireForAndroid {
    public static final int COLD_START = 16384008;
    public static final int COLD_START_DEX_LOAD = 16384005;
    public static final int COLD_START_LOGIN = 16384009;
    public static final int GRAPHAPI_CONTACT_SYNC = 16384002;
    public static final int GRAPHAPI_FRIEND_SEARCH = 16384007;
    public static final int GRAPHAPI_FRIEND_SUGGESTIONS = 16384004;
    public static final int GRAPHAPI_LOGIN = 16384001;
    public static final int GRAPHAPI_UPLOAD_CONTACTS = 16384003;
    public static final int GRAPHAPI_UPLOAD_CONTACTS_BATCHING_E2E = 16384011;
    public static final int GRAPHQL_QUERY = 16384012;
    public static final short MODULE_ID = 250;
    public static final int MULTIWAY_JOIN = 16384010;
    public static final int SCROLL_PERF = 16384006;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "BONFIRE_FOR_ANDROID_GRAPHAPI_LOGIN";
            case 2:
                return "BONFIRE_FOR_ANDROID_GRAPHAPI_CONTACT_SYNC";
            case 3:
                return "BONFIRE_FOR_ANDROID_GRAPHAPI_UPLOAD_CONTACTS";
            case 4:
                return "BONFIRE_FOR_ANDROID_GRAPHAPI_FRIEND_SUGGESTIONS";
            case 5:
                return "BONFIRE_FOR_ANDROID_COLD_START_DEX_LOAD";
            case 6:
                return "BONFIRE_FOR_ANDROID_SCROLL_PERF";
            case 7:
                return "BONFIRE_FOR_ANDROID_GRAPHAPI_FRIEND_SEARCH";
            case 8:
                return "BONFIRE_FOR_ANDROID_COLD_START";
            case 9:
                return "BONFIRE_FOR_ANDROID_COLD_START_LOGIN";
            case 10:
                return "BONFIRE_FOR_ANDROID_MULTIWAY_JOIN";
            case 11:
                return "BONFIRE_FOR_ANDROID_GRAPHAPI_UPLOAD_CONTACTS_BATCHING_E2E";
            case 12:
                return "BONFIRE_FOR_ANDROID_GRAPHQL_QUERY";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
