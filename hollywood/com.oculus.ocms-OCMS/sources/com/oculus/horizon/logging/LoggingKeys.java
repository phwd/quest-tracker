package com.oculus.horizon.logging;

public class LoggingKeys {
    public static final String ACTIVE_DURATION_MS = "active_duration_ms";
    public static final String APP_PACKAGE_NAME = "app_package_name";
    public static final String APP_VERSION_CODE = "app_version_code";
    public static final String APP_VERSION_NAME = "app_version_name";
    public static final String AUDIO_INPUT_FILE_SIZE = "audio_input_file_size";
    public static final String CATEGORY_ID = "category_id";
    public static final String CRITERIA_ID = "criteria_id";
    public static final String ERROR_CODE = "error_code";
    public static final String EVENT_VALUE = "event_value";
    public static final String FORCE_SYNC_EXECUTION_DELAY = "exec_delay";
    public static final String FORCE_SYNC_FAILURE_REASON = "reason";
    public static final String FORCE_SYNC_REQUEST_ID = "request_id";
    public static final String FORCE_SYNC_WAKEUP_DEVICE = "wakeup_device";
    public static final String INSTALL_ID = "install_id";
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_STATUS = "item_status";
    public static final String METHOD = "method";
    public static final String MUXED_VIDEO_FILE_SIZE = "muxed_video_file_size";
    public static final String NOTIFICATION_MEDIUM = "notification_medium";
    public static final String NOTIFICATION_PREFERENCE_CHANGE_LOCATION = "location";
    public static final String NOTIFICATION_PREFERENCE_TYPE = "preference_type";
    public static final String OPT_OUT = "opt_out";
    public static final String PARAM_PREFIX = "param_";
    public static final String PATH = "path";
    public static final String REASON = "reason";
    public static final String RECORDER_ERROR = "error";
    public static final String RECORDER_SOURCE = "source";
    public static final String RECORDING_UUID = "recording_uuid";
    public static final String REFERRER = "referrer";
    public static final String REFERRER_CAMPAIGN = "campaign";
    public static final String REFERRER_CONTENT = "content";
    public static final String REFERRER_MEDIUM = "medium";
    public static final String REFERRER_SOURCE = "source";
    public static final String REFERRER_TERM = "term";
    public static final String REPORT_ID = "report_id";
    public static final String RETRY_NUMBER = "retry_number";
    public static final String SEARCH_QUERY = "search_query";
    public static final String SOURCE = "source";
    public static final String SUCCESS = "success";
    public static final String TEARDOWN_INTERVAL_MS = "teardown_interval_ms";
    public static final String UPLOAD_SESSION_ID = "upload_session_id";
    public static final String URI = "uri";
    public static final String VIDEO_INPUT_FILE_SIZE = "video_input_file_size";

    public enum ActivityPrivacyRoadblockEvents {
        CONTINUE,
        FINISH,
        LEARN_MORE,
        SKIP
    }

    public static class FACEBOOK_REAUTH_KEYS {
        public static final String ERROR_CODE = "error_code";
        public static final String ERROR_MESSAGE = "error_message";
        public static final String ERROR_SUBCODE = "error_subcode";
        public static final String IS_REFRESHED = "is_refreshed";
        public static final String IS_VALID_AFTER_REAUTH = "is_valid_after_reauth";
        public static final String IS_VALID_BEFORE_REAUTH = "is_valid_before_reauth";
    }

    public enum FBLoginFlow {
        SSO,
        SDK
    }

    public static class StandaloneSetupAppInstall {
        public static final String DOWNLOAD_FAILURE = "download_failure";
        public static final String HIGH_PRI_APPS_SETUP = "high_priority_apps_download";
        public static final String IGNORED = "ignored";
        public static final String INSTALL_FAILURE = "install_failure";
        public static final String NUM_DOWNLOAD_FAILURE = "num_download_failure";
        public static final String NUM_IGNORED = "num_ignored";
        public static final String NUM_INSTALL_FAILURE = "num_install_failure";
        public static final String NUM_SUCCESS = "num_install_success";
        public static final String NUM_UNKNOWN = "num_unkown";
        public static final String SUCCESS = "install_success";
        public static final String UNKNOWN = "unknown";
    }
}
