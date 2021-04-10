package com.facebook.quicklog.identifiers;

public class IgDownloadableStrings {
    public static final int IG_LANGPACK_DOD_FETCH_ANDROID = 37879810;
    public static final int IG_RESOURCES_LOADING_DOWNLOADED_STRINGS = 37879809;
    public static final int IG_RESOURCES_WAITING_ACTIVITY = 37879811;
    public static final short MODULE_ID = 578;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "IG_DOWNLOADABLE_STRINGS_IG_RESOURCES_WAITING_ACTIVITY" : "IG_DOWNLOADABLE_STRINGS_IG_LANGPACK_DOD_FETCH_ANDROID" : "IG_DOWNLOADABLE_STRINGS_IG_RESOURCES_LOADING_DOWNLOADED_STRINGS";
    }
}
