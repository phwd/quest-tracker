package com.facebook.quicklog.identifiers;

public class AppUpgrade {
    public static final short MODULE_ID = 312;
    public static final int SELF_UPDATE_FETCH_RELEASE_INFO_BY_FQL = 20447233;
    public static final int SELF_UPDATE_FETCH_RELEASE_INFO_BY_GRAPH_API = 20447234;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "APP_UPGRADE_SELF_UPDATE_FETCH_RELEASE_INFO_BY_GRAPH_API" : "APP_UPGRADE_SELF_UPDATE_FETCH_RELEASE_INFO_BY_FQL";
    }
}
