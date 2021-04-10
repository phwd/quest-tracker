package com.oculus.library.model;

public enum CloudStorageStatus {
    DISABLED,
    ENABLED,
    UPLOAD_SYNC_REQUIRED,
    UPLOAD_SYNCING,
    DOWNLOAD_SYNCING;

    public static boolean isEnabled(CloudStorageStatus cloudStorageStatus) {
        return cloudStorageStatus != DISABLED;
    }

    public static boolean isSyncing(CloudStorageStatus cloudStorageStatus) {
        return cloudStorageStatus == UPLOAD_SYNCING || cloudStorageStatus == DOWNLOAD_SYNCING;
    }

    public static boolean isUploadSyncRequired(CloudStorageStatus cloudStorageStatus) {
        return cloudStorageStatus == UPLOAD_SYNC_REQUIRED;
    }

    public static CloudStorageStatus fromData(CloudStorageStatus cloudStorageStatus, boolean z) {
        if (cloudStorageStatus == ENABLED || cloudStorageStatus == DISABLED) {
            return z ? ENABLED : DISABLED;
        }
        return cloudStorageStatus;
    }
}
