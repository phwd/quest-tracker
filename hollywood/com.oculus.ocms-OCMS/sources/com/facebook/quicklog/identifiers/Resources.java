package com.facebook.quicklog.identifiers;

public class Resources {
    public static final int FBT_LANGPACK_DOD_FETCH_ANDROID = 4456459;
    public static final int FB_QT_RESOURCES_DOWNLOAD = 4456455;
    public static final int FB_QT_RESOURCES_LOADING = 4456456;
    public static final int FB_QT_RESOURCES_PROCESS_NEW = 4456457;
    public static final int FB_REACT_NATIVE_RESOURCES_DOWNLOAD_FILE = 4456458;
    public static final int FB_RESOURCES_DOWNLOAD_FILE = 4456451;
    public static final int FB_RESOURCES_LOADING_ASSET_STRINGS = 4456449;
    public static final int FB_RESOURCES_LOADING_DOWNLOADED_STRINGS = 4456450;
    public static final int FB_RESOURCES_WAITING_ACTIVITY = 4456452;
    public static final short MODULE_ID = 68;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "RESOURCES_FB_RESOURCES_LOADING_ASSET_STRINGS";
            case 2:
                return "RESOURCES_FB_RESOURCES_LOADING_DOWNLOADED_STRINGS";
            case 3:
                return "RESOURCES_FB_RESOURCES_DOWNLOAD_FILE";
            case 4:
                return "RESOURCES_FB_RESOURCES_WAITING_ACTIVITY";
            case 5:
            case 6:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 7:
                return "RESOURCES_FB_QT_RESOURCES_DOWNLOAD";
            case 8:
                return "RESOURCES_FB_QT_RESOURCES_LOADING";
            case 9:
                return "RESOURCES_FB_QT_RESOURCES_PROCESS_NEW";
            case 10:
                return "RESOURCES_FB_REACT_NATIVE_RESOURCES_DOWNLOAD_FILE";
            case 11:
                return "RESOURCES_FBT_LANGPACK_DOD_FETCH_ANDROID";
        }
    }
}
