package com.oculus.cloudstoragehelper;

public class CloudStorageHelper {
    public static final String EXTRA_KEY_APP_ID = "app_id";
    public static final String EXTRA_KEY_CALLBACK_RECEIVER = "callback_receiver";
    public static final String EXTRA_KEY_CONFLICT_RESOLUTION = "conflict_resolution";
    public static final String EXTRA_KEY_WORK_TYPE = "work_type";
    public static final String TAG = "CloudStorageHelper";
    public static final String TARGET_INTENT_SERVICE_CLASS = "com.oculus.horizon.cloudstorage2.CloudStorageIntentService";
    public static final String TARGET_SERVICE_PACKAGE = "com.oculus.horizon";

    public enum JobType {
        UPLOAD,
        DOWNLOAD
    }

    public enum ResolutionType {
        USE_LOCAL,
        USE_REMOTE
    }

    public enum RunType {
        LAUNCH_SYNC,
        RESOLVE_CONFLICT
    }
}
