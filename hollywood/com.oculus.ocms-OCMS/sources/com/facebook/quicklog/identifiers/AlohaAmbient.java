package com.facebook.quicklog.identifiers;

public class AlohaAmbient {
    public static final int CONTENT_PROVIDER_OPERATION = 928057350;
    public static final int INITIAL_FEED_LOAD = 928068722;
    public static final short MODULE_ID = 14161;
    public static final int MODULE_PUBLISH_TIME = 928056633;
    public static final int USER_SWITCH_FEED_LOAD = 928069829;

    public static String getMarkerName(int i) {
        return i != 1337 ? i != 2054 ? i != 13426 ? i != 14533 ? "UNDEFINED_QPL_EVENT" : "ALOHA_AMBIENT_USER_SWITCH_FEED_LOAD" : "ALOHA_AMBIENT_INITIAL_FEED_LOAD" : "ALOHA_AMBIENT_CONTENT_PROVIDER_OPERATION" : "ALOHA_AMBIENT_MODULE_PUBLISH_TIME";
    }
}
