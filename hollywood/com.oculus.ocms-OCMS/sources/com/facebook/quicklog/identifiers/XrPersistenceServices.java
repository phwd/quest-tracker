package com.facebook.quicklog.identifiers;

public class XrPersistenceServices {
    public static final int CONTENT_FETCH = 41091075;
    public static final short MODULE_ID = 627;
    public static final int RELOCALIZATION = 41091074;
    public static final int REMOTE_POSE_PUBLISH = 41091076;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "XR_PERSISTENCE_SERVICES_REMOTE_POSE_PUBLISH" : "XR_PERSISTENCE_SERVICES_CONTENT_FETCH" : "XR_PERSISTENCE_SERVICES_RELOCALIZATION";
    }
}
