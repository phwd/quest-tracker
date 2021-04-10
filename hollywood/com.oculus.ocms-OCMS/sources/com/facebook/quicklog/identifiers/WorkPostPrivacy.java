package com.facebook.quicklog.identifiers;

public class WorkPostPrivacy {
    public static final short MODULE_ID = 942;
    public static final int PRIVACY_CHANGE = 61734913;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "WORK_POST_PRIVACY_PRIVACY_CHANGE";
    }
}
