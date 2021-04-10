package com.facebook.quicklog.identifiers;

public class Vault {
    public static final short MODULE_ID = 58;
    public static final int PERF_TIME_TO_GET_SYNCED_PHOTO_HASH_TO_FBID = 3801089;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "VAULT_PERF_TIME_TO_GET_SYNCED_PHOTO_HASH_TO_FBID";
    }
}
