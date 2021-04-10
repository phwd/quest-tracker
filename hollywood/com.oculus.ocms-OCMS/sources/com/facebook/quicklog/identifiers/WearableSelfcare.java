package com.facebook.quicklog.identifiers;

public class WearableSelfcare {
    public static final int FINISH_SESSION = 704391207;
    public static final short MODULE_ID = 10748;
    public static final int NEW_LAP = 704384809;
    public static final int NEW_SESSION = 704385731;
    public static final int PAUSE_LAP = 704393632;
    public static final int QUERY_ACTIVITY_METRICS = 704386646;
    public static final int RESUME_LAP = 704382206;
    public static final int SERVICE_START = 704394609;
    public static final int SESSION_QUERY = 704385115;
    public static final int SUBSCRIBE_ACTIVITY_METRICS = 704389190;
    public static final int UNSUBCRIBE_ACTIVITY_METRICS = 704386397;

    public static String getMarkerName(int i) {
        return i != 1278 ? i != 3881 ? i != 4187 ? i != 4803 ? i != 5469 ? i != 5718 ? i != 8262 ? i != 10279 ? i != 12704 ? i != 13681 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_SELFCARE_SERVICE_START" : "WEARABLE_SELFCARE_PAUSE_LAP" : "WEARABLE_SELFCARE_FINISH_SESSION" : "WEARABLE_SELFCARE_SUBSCRIBE_ACTIVITY_METRICS" : "WEARABLE_SELFCARE_QUERY_ACTIVITY_METRICS" : "WEARABLE_SELFCARE_UNSUBCRIBE_ACTIVITY_METRICS" : "WEARABLE_SELFCARE_NEW_SESSION" : "WEARABLE_SELFCARE_SESSION_QUERY" : "WEARABLE_SELFCARE_NEW_LAP" : "WEARABLE_SELFCARE_RESUME_LAP";
    }
}
