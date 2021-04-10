package com.facebook.quicklog.identifiers;

public class DownloadableModules {
    public static final int DOWNLOAD_AND_UNPACK = 34668546;
    public static final int DOWNLOAD_MODULES = 34668545;
    public static final short MODULE_ID = 529;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "DOWNLOADABLE_MODULES_DOWNLOAD_AND_UNPACK" : "DOWNLOADABLE_MODULES_DOWNLOAD_MODULES";
    }
}
