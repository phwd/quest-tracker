package com.facebook.quicklog.identifiers;

public class CompactDisk {
    public static final int COMPACT_DISK_SUMMARY_STATS = 10420235;
    public static final int DISK_CACHE_FAILURE = 10420230;
    public static final int DISK_CACHE_INITIALIZE = 10420234;
    public static final int DISK_CACHE_INSERT = 10420231;
    public static final int DISK_CACHE_MISS = 10420229;
    public static final int DISK_CACHE_REMOVE = 10420232;
    public static final int DISK_CACHE_TRASH_COLLECTION = 10420233;
    public static final short MODULE_ID = 159;

    public static String getMarkerName(int i) {
        switch (i) {
            case 5:
                return "COMPACT_DISK_DISK_CACHE_MISS";
            case 6:
                return "COMPACT_DISK_DISK_CACHE_FAILURE";
            case 7:
                return "COMPACT_DISK_DISK_CACHE_INSERT";
            case 8:
                return "COMPACT_DISK_DISK_CACHE_REMOVE";
            case 9:
                return "COMPACT_DISK_DISK_CACHE_TRASH_COLLECTION";
            case 10:
                return "COMPACT_DISK_DISK_CACHE_INITIALIZE";
            case 11:
                return "COMPACT_DISK_COMPACT_DISK_SUMMARY_STATS";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
