package com.facebook.quicklog.identifiers;

public class PrivacyInvalidation {
    public static final int LIVEQUERY_INVALIDATED = 14680068;
    public static final int LIVEQUERY_SEEN_ALREADY = 14680070;
    public static final int LIVEQUERY_STORY_COUNT = 14680069;
    public static final short MODULE_ID = 224;
    public static final int POLLING_INVALIDATED = 14680066;
    public static final int POLLING_SEEN_ALREADY = 14680067;
    public static final int POLLING_STORY_COUNT = 14680065;
    public static final int PRIVACY_INVALIDATION_PERF = 14680071;
    public static final int PRIVACY_SUBSCRIPTION_PERF = 14680072;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "PRIVACY_INVALIDATION_POLLING_STORY_COUNT";
            case 2:
                return "PRIVACY_INVALIDATION_POLLING_INVALIDATED";
            case 3:
                return "PRIVACY_INVALIDATION_POLLING_SEEN_ALREADY";
            case 4:
                return "PRIVACY_INVALIDATION_LIVEQUERY_INVALIDATED";
            case 5:
                return "PRIVACY_INVALIDATION_LIVEQUERY_STORY_COUNT";
            case 6:
                return "PRIVACY_INVALIDATION_LIVEQUERY_SEEN_ALREADY";
            case 7:
                return "PRIVACY_INVALIDATION_PRIVACY_INVALIDATION_PERF";
            case 8:
                return "PRIVACY_INVALIDATION_PRIVACY_SUBSCRIPTION_PERF";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
