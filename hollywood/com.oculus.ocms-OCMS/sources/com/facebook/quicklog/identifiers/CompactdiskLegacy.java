package com.facebook.quicklog.identifiers;

public class CompactdiskLegacy {
    public static final int GET_DISK_CACHE = 27852801;
    public static final short MODULE_ID = 425;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "COMPACTDISK_LEGACY_GET_DISK_CACHE";
    }
}
