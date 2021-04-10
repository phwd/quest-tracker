package com.oculus.library.model;

public enum CloudStorageStatus {
    DISABLED,
    ENABLED,
    UPLOAD_SYNC_REQUIRED,
    UPLOAD_SYNCING,
    DOWNLOAD_SYNCING;

    public static boolean isEnabled(CloudStorageStatus status) {
        return status != DISABLED;
    }

    public static boolean isSyncing(CloudStorageStatus status) {
        return status == UPLOAD_SYNCING || status == DOWNLOAD_SYNCING;
    }

    public static boolean isUploadSyncRequired(CloudStorageStatus status) {
        return status == UPLOAD_SYNC_REQUIRED;
    }

    public static CloudStorageStatus fromData(CloudStorageStatus cachedStatus, boolean isEnabledOnServer) {
        if (cachedStatus != ENABLED && cachedStatus != DISABLED) {
            return cachedStatus;
        }
        return isEnabledOnServer ? ENABLED : DISABLED;
    }
}
