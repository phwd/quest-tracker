package com.facebook.quicklog.identifiers;

public class ApplicationModules {
    public static final int API_EXECUTE = 11337741;
    public static final int API_EXECUTE_BATCH = 11337743;
    public static final int DOWNLOAD_ACTIVITY_VISIBLE = 11337734;
    public static final int DOWNLOAD_MODULE = 11337731;
    public static final int DOWNLOAD_MODULE_BATCH = 11337732;
    public static final int FAILOVER_DOWNLOADER = 11337742;
    public static final int FBLITE_DOWNLOAD_MODULE = 11337738;
    public static final int FBLITE_DOWNLOAD_MODULE_BATCH = 11337739;
    public static final int FBLITE_GET_MODULE_METADATA = 11337740;
    public static final int FBLITE_LOAD_MODULE = 11337737;
    public static final int FBLITE_OPEN_MODULE = 11337736;
    public static final int GET_MODULE_METADATA = 11337733;
    public static final int LOAD_MODULE = 11337730;
    public static final short MODULE_ID = 173;
    public static final int OPEN_MODULE = 11337729;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "APPLICATION_MODULES_OPEN_MODULE";
            case 2:
                return "APPLICATION_MODULES_LOAD_MODULE";
            case 3:
                return "APPLICATION_MODULES_DOWNLOAD_MODULE";
            case 4:
                return "APPLICATION_MODULES_DOWNLOAD_MODULE_BATCH";
            case 5:
                return "APPLICATION_MODULES_GET_MODULE_METADATA";
            case 6:
                return "APPLICATION_MODULES_DOWNLOAD_ACTIVITY_VISIBLE";
            case 7:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 8:
                return "APPLICATION_MODULES_FBLITE_OPEN_MODULE";
            case 9:
                return "APPLICATION_MODULES_FBLITE_LOAD_MODULE";
            case 10:
                return "APPLICATION_MODULES_FBLITE_DOWNLOAD_MODULE";
            case 11:
                return "APPLICATION_MODULES_FBLITE_DOWNLOAD_MODULE_BATCH";
            case 12:
                return "APPLICATION_MODULES_FBLITE_GET_MODULE_METADATA";
            case 13:
                return "APPLICATION_MODULES_API_EXECUTE";
            case 14:
                return "APPLICATION_MODULES_FAILOVER_DOWNLOADER";
            case 15:
                return "APPLICATION_MODULES_API_EXECUTE_BATCH";
        }
    }
}
