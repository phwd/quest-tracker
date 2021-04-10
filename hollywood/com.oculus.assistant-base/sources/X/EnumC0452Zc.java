package X;

/* renamed from: X.Zc  reason: case insensitive filesystem */
public enum EnumC0452Zc {
    DISABLED,
    ENABLED,
    UPLOAD_SYNC_REQUIRED,
    UPLOAD_SYNCING,
    DOWNLOAD_SYNCING;

    public static EnumC0452Zc fromData(EnumC0452Zc zc, boolean z) {
        EnumC0452Zc zc2 = ENABLED;
        if (zc != zc2 && zc != DISABLED) {
            return zc;
        }
        if (z) {
            return zc2;
        }
        return DISABLED;
    }

    public static boolean isEnabled(EnumC0452Zc zc) {
        if (zc != DISABLED) {
            return true;
        }
        return false;
    }

    public static boolean isSyncing(EnumC0452Zc zc) {
        if (zc == UPLOAD_SYNCING || zc == DOWNLOAD_SYNCING) {
            return true;
        }
        return false;
    }

    public static boolean isUploadSyncRequired(EnumC0452Zc zc) {
        if (zc == UPLOAD_SYNC_REQUIRED) {
            return true;
        }
        return false;
    }
}
