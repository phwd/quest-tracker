package com.facebook.quicklog.identifiers;

public class LocalSearchAndroid {
    public static final int LOAD_MAP_CARD_ANDROID = 22216705;
    public static final int MAP_PIN_QUERY_ANDROID = 22216706;
    public static final short MODULE_ID = 339;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "LOCAL_SEARCH_ANDROID_MAP_PIN_QUERY_ANDROID" : "LOCAL_SEARCH_ANDROID_LOAD_MAP_CARD_ANDROID";
    }
}
