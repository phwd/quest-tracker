package com.facebook.quicklog.identifiers;

public class FeedGapValidation {
    public static final int GAP_RULES_DIFFERENT = 55050241;
    public static final int GAP_RULE_CLIENT_FALLBACK = 55050242;
    public static final short MODULE_ID = 840;
    public static final int POOL_GAP_RULE_VALIDATION = 55057822;
    public static final int UI_GAP_RULE_VALIDATION_RESULT = 55063668;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 7582 ? i != 13428 ? "UNDEFINED_QPL_EVENT" : "FEED_GAP_VALIDATION_UI_GAP_RULE_VALIDATION_RESULT" : "FEED_GAP_VALIDATION_POOL_GAP_RULE_VALIDATION" : "FEED_GAP_VALIDATION_GAP_RULE_CLIENT_FALLBACK" : "FEED_GAP_VALIDATION_GAP_RULES_DIFFERENT";
    }
}
