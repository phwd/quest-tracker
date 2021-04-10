package com.oculus.library.model;

public enum CloudStorageStatus {
    DISABLED,
    ENABLED,
    UPLOAD_SYNC_REQUIRED,
    UPLOAD_SYNCING,
    DOWNLOAD_SYNCING;

    public static CloudStorageStatus fromData(CloudStorageStatus cloudStorageStatus, boolean z) {
        CloudStorageStatus cloudStorageStatus2 = ENABLED;
        if (cloudStorageStatus != cloudStorageStatus2 && cloudStorageStatus != DISABLED) {
            return cloudStorageStatus;
        }
        if (z) {
            return cloudStorageStatus2;
        }
        return DISABLED;
    }

    public static boolean isEnabled(CloudStorageStatus cloudStorageStatus) {
        if (cloudStorageStatus != DISABLED) {
            return true;
        }
        return false;
    }

    public static boolean isSyncing(CloudStorageStatus cloudStorageStatus) {
        if (cloudStorageStatus == UPLOAD_SYNCING || cloudStorageStatus == DOWNLOAD_SYNCING) {
            return true;
        }
        return false;
    }

    public static boolean isUploadSyncRequired(CloudStorageStatus cloudStorageStatus) {
        if (cloudStorageStatus == UPLOAD_SYNC_REQUIRED) {
            return true;
        }
        return false;
    }
}
