package com.facebook.quicklog.identifiers;

public class PrivacyActivityLogTti {
    public static final int ACTIVITY_LOG_FAIL = 22675458;
    public static final int ACTIVITY_LOG_SUCCESS = 22675457;
    public static final int LOAD_CATEGORY_STORIES = 22675460;
    public static final int LOAD_CATEGORY_STORIES_RN = 22675461;
    public static final short MODULE_ID = 346;
    public static final int PRIVACY_ACTIVITY_LOG_TTI = 22675459;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "PRIVACY_ACTIVITY_LOG_TTI_LOAD_CATEGORY_STORIES_RN" : "PRIVACY_ACTIVITY_LOG_TTI_LOAD_CATEGORY_STORIES" : "PRIVACY_ACTIVITY_LOG_TTI_PRIVACY_ACTIVITY_LOG_TTI" : "PRIVACY_ACTIVITY_LOG_TTI_ACTIVITY_LOG_FAIL" : "PRIVACY_ACTIVITY_LOG_TTI_ACTIVITY_LOG_SUCCESS";
    }
}
