package com.facebook.quicklog.identifiers;

public class ReactOtaUpdate {
    public static final int CHECK_OTA_UPDATE = 32905824;
    public static final short MODULE_ID = 502;
    public static final int OTA_DOWNLOAD_PROCESS = 32899073;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 6752 ? "UNDEFINED_QPL_EVENT" : "REACT_OTA_UPDATE_CHECK_OTA_UPDATE" : "REACT_OTA_UPDATE_OTA_DOWNLOAD_PROCESS";
    }
}
