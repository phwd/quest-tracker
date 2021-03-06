package com.facebook.quicklog.identifiers;

public class ProfileReliability {
    public static final short MODULE_ID = 510;
    public static final int PROFILE_COVER_PHOTO_RELIABILITY_ANDROID = 33423362;
    public static final int PROFILE_LOAD_RELIABILITY = 33423364;
    public static final int PROFILE_PICTURE_UPLOAD_RELIABILITY_ANDROID = 33423365;
    public static final int PROFILE_PROFILE_PICTURE_RELIABILITY_ANDROID = 33423363;
    public static final int PROFILE_TAIL_LOAD_RELIABILITY = 33423366;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 6 ? "UNDEFINED_QPL_EVENT" : "PROFILE_RELIABILITY_PROFILE_TAIL_LOAD_RELIABILITY" : "PROFILE_RELIABILITY_PROFILE_PICTURE_UPLOAD_RELIABILITY_ANDROID" : "PROFILE_RELIABILITY_PROFILE_LOAD_RELIABILITY" : "PROFILE_RELIABILITY_PROFILE_PROFILE_PICTURE_RELIABILITY_ANDROID" : "PROFILE_RELIABILITY_PROFILE_COVER_PHOTO_RELIABILITY_ANDROID";
    }
}
