package com.facebook.quicklog.identifiers;

public class GraphqlAndroidConnectionFetcher {
    public static final int CONFIGURATION_GET_REQUEST = 9437185;
    public static final int CONFIGURATION_GET_ROWS = 9437186;
    public static final short MODULE_ID = 144;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "GRAPHQL_ANDROID_CONNECTION_FETCHER_CONFIGURATION_GET_ROWS" : "GRAPHQL_ANDROID_CONNECTION_FETCHER_CONFIGURATION_GET_REQUEST";
    }
}
