package com.facebook.quicklog.identifiers;

public class WearableBackup {
    public static final int BACKUP = 416757165;
    public static final int COLD_START = 416755066;
    public static final short MODULE_ID = 6359;
    public static final int REGISTER_PERIODIC_BACKUP = 416745677;
    public static final int RESTORE = 416747632;
    public static final int UNREGISTER_PERIODIC_BACKUP = 416752180;

    public static String getMarkerName(int i) {
        return i != 2253 ? i != 4208 ? i != 8756 ? i != 11642 ? i != 13741 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_BACKUP_BACKUP" : "WEARABLE_BACKUP_COLD_START" : "WEARABLE_BACKUP_UNREGISTER_PERIODIC_BACKUP" : "WEARABLE_BACKUP_RESTORE" : "WEARABLE_BACKUP_REGISTER_PERIODIC_BACKUP";
    }
}
