package com.facebook.quicklog.identifiers;

public class TouchResponsiveness {
    public static final short MODULE_ID = 373;
    public static final int TOUCH_DELAY_ANDROID = 24444931;
    public static final int TOUCH_RESPONSIVENESS_ANDROID = 24444929;
    public static final int TOUCH_RESPONSIVENESS_TRACE_COLLECTION = 24444932;
    public static final int TR_SESSION = 24458555;
    public static final int USER_STATUS_IPC_QUERY = 24461564;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 13627 ? i != 16636 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "TOUCH_RESPONSIVENESS_TOUCH_RESPONSIVENESS_TRACE_COLLECTION" : "TOUCH_RESPONSIVENESS_TOUCH_DELAY_ANDROID" : "TOUCH_RESPONSIVENESS_USER_STATUS_IPC_QUERY" : "TOUCH_RESPONSIVENESS_TR_SESSION" : "TOUCH_RESPONSIVENESS_TOUCH_RESPONSIVENESS_ANDROID";
    }
}
