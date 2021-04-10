package com.facebook.quicklog.identifiers;

public class PlatformSharing {
    public static final short MODULE_ID = 785;
    public static final int PLATFORM_COMPOSER_LAUNCH_PERF = 51445761;
    public static final int PLATFORM_SHARE_DIALOG = 51452317;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 6557 ? "UNDEFINED_QPL_EVENT" : "PLATFORM_SHARING_PLATFORM_SHARE_DIALOG" : "PLATFORM_SHARING_PLATFORM_COMPOSER_LAUNCH_PERF";
    }
}
