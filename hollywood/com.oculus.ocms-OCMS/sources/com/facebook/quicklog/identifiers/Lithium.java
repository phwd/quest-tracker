package com.facebook.quicklog.identifiers;

public class Lithium {
    public static final int LIVESET = 13697032;
    public static final int LIVESET_FETCH = 13697031;
    public static final short MODULE_ID = 209;
    public static final int PRIVACY_INVALIDATION_STORIES = 13697027;
    public static final int PRIVACY_INVALIDATION_SUBSCRIBED = 13697025;
    public static final int RSOCKET_CONNECT = 13697028;
    public static final int RSOCKET_CONNECTION = 13697029;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3 ? i != 4 ? i != 5 ? i != 7 ? i != 8 ? "UNDEFINED_QPL_EVENT" : "LITHIUM_LIVESET" : "LITHIUM_LIVESET_FETCH" : "LITHIUM_RSOCKET_CONNECTION" : "LITHIUM_RSOCKET_CONNECT" : "LITHIUM_PRIVACY_INVALIDATION_STORIES" : "LITHIUM_PRIVACY_INVALIDATION_SUBSCRIBED";
    }
}
