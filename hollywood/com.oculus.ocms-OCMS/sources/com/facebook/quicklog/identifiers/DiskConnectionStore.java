package com.facebook.quicklog.identifiers;

public class DiskConnectionStore {
    public static final int CONSISTENCY_NOTIFICATION = 9568265;
    public static final int DISKCONNECTIONSTORE_ADD_PAGE = 9568257;
    public static final int DISKCONNECTIONSTORE_INITIALIZE = 9568258;
    public static final short MODULE_ID = 146;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 9 ? "UNDEFINED_QPL_EVENT" : "DISK_CONNECTION_STORE_CONSISTENCY_NOTIFICATION" : "DISK_CONNECTION_STORE_DISKCONNECTIONSTORE_INITIALIZE" : "DISK_CONNECTION_STORE_DISKCONNECTIONSTORE_ADD_PAGE";
    }
}
