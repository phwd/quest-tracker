package com.facebook.quicklog.identifiers;

public class IgContentFilterEngine {
    public static final int ADD_REMOVE_PATTERN = 893650399;
    public static final int FIND_PATTERN_ALL = 893649979;
    public static final int FIND_PATTERN_SINGLE = 893660699;
    public static final short MODULE_ID = 13636;
    public static final int SPIN_UP_ENGINE = 893661203;

    public static String getMarkerName(int i) {
        return i != 1083 ? i != 1503 ? i != 11803 ? i != 12307 ? "UNDEFINED_QPL_EVENT" : "IG_CONTENT_FILTER_ENGINE_SPIN_UP_ENGINE" : "IG_CONTENT_FILTER_ENGINE_FIND_PATTERN_SINGLE" : "IG_CONTENT_FILTER_ENGINE_ADD_REMOVE_PATTERN" : "IG_CONTENT_FILTER_ENGINE_FIND_PATTERN_ALL";
    }
}
