package com.facebook.quicklog.identifiers;

public class Stash {
    public static final int APP_BACKGROUND = 42991643;
    public static final int APP_HEARTBEAT = 42991642;
    public static final int CREATE_STASH = 42991640;
    public static final int CREATE_STASH_MANAGER = 42991641;
    public static final int EMPTY_TRASH = 42991644;
    public static final int GET_ALL_KEYS = 42991637;
    public static final int GET_ITEM_COUNT = 42991649;
    public static final int GET_RESOURCE = 42991628;
    public static final int GET_RESOURCE_PATH = 42991648;
    public static final int GET_SIZE_BYTES = 42991638;
    public static final int HAS_KEY = 42991636;
    public static final int INSERT = 42991629;
    public static final int MLEVICTION_LOG_LABEL = 43004693;
    public static final int MLEVICTION_PATHS_TO_EVICT = 43004452;
    public static final int MLEVICTION_PREDICT = 43006695;
    public static final int MLEVICTION_TRAIN = 42998621;
    public static final short MODULE_ID = 656;
    public static final int READ_RESOURCE = 42991645;
    public static final int REMOVE = 42991635;
    public static final int REMOVE_ALL = 42991639;
    public static final int STASH_HAS_KEY = 42991633;
    public static final int STASH_INSERT = 42991631;
    public static final int TOUCH = 42993851;
    public static final int WRITE = 42991646;

    public static String getMarkerName(int i) {
        if (i == 12) {
            return "STASH_GET_RESOURCE";
        }
        if (i == 13) {
            return "STASH_INSERT";
        }
        if (i == 15) {
            return "STASH_STASH_INSERT";
        }
        if (i == 17) {
            return "STASH_STASH_HAS_KEY";
        }
        if (i == 2235) {
            return "STASH_TOUCH";
        }
        if (i == 7005) {
            return "STASH_MLEVICTION_TRAIN";
        }
        if (i == 12836) {
            return "STASH_MLEVICTION_PATHS_TO_EVICT";
        }
        if (i == 13077) {
            return "STASH_MLEVICTION_LOG_LABEL";
        }
        if (i == 15079) {
            return "STASH_MLEVICTION_PREDICT";
        }
        if (i == 32) {
            return "STASH_GET_RESOURCE_PATH";
        }
        if (i == 33) {
            return "STASH_GET_ITEM_COUNT";
        }
        switch (i) {
            case 19:
                return "STASH_REMOVE";
            case 20:
                return "STASH_HAS_KEY";
            case 21:
                return "STASH_GET_ALL_KEYS";
            case 22:
                return "STASH_GET_SIZE_BYTES";
            case 23:
                return "STASH_REMOVE_ALL";
            case 24:
                return "STASH_CREATE_STASH";
            case 25:
                return "STASH_CREATE_STASH_MANAGER";
            case 26:
                return "STASH_APP_HEARTBEAT";
            case 27:
                return "STASH_APP_BACKGROUND";
            case 28:
                return "STASH_EMPTY_TRASH";
            case 29:
                return "STASH_READ_RESOURCE";
            case 30:
                return "STASH_WRITE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
