package com.oculus.appmanager.installer.contract.errors;

public class ErrorCategories {
    public static final String ASSET = "asset_";
    public static final String ASSET_TOO_MANY_FOUND = "asset_too_many_assets_found";
    public static final String CONSISTENCY = "consistency_";
    public static final String CONSISTENCY_CHECK_FAILED = "consistency_check_failed";
    public static final String CONSISTENCY_DELETING_TIMED_OUT = "consistency_deleting_timed_out";
    public static final String CONSISTENCY_DOWNLOADED_TIMED_SIZE = "consistency_downloaded_timed_out";
    public static final String CONSISTENCY_DOWNLOAD_DEAD = "consistency_coldstart_download_dead";
    public static final String CONSISTENCY_DOWNLOAD_ID_MISSING = "consistency_download_id_missing";
    public static final String CONSISTENCY_DOWNLOAD_INFO_MISSING = "consistency_download_info_missing";
    public static final String CONSISTENCY_DOWNLOAD_MISSING_FILE = "consistency_download_missing_file";
    public static final String CONSISTENCY_DOWNLOAD_READ_PERMISSION_NOT_GRANTED = "consistency_download_read_permission_not_granted";
    public static final String CONSISTENCY_DOWNLOAD_SECURITY_EXCEPTION = "consistency_download_security_exception";
    public static final String CONSISTENCY_DOWNLOAD_WRONG_SIZE = "consistency_download_wrong_size";
    public static final String CONSISTENCY_INSTALL_INTERRUPTED = "consistency_install_interrupted";
    public static final String CONSISTENCY_INSTALL_OBSOLETE = "consistency_install_obsolete";
    public static final String CONSISTENCY_INSTALL_TIMED_OUT = "consistency_install_timed_out";
    public static final String CONSISTENCY_JOB_SCHEDULER_NOT_FOUND = "consistency_job_scheduler_not_found";
    public static final String CONSISTENCY_JOB_SCHEDULER_NO_ID_AVAILABLE = "consistency_job_scheduler_no_id_available";
    public static final String CONSISTENCY_RECOVERY_FAILURE = "consistency_recovery_failure";
    public static final String CONSISTENCY_TIMED_OUT = "consistency_timed_out";
    public static final String CONSISTENCY_VERIFIED_TIMED_OUT = "consistency_verified_timed_out";
    public static final String CONSISTENCY_VERIFYING_TIMED_OUT = "consistency_verifying_timed_out";
    public static final String DOWNLOAD = "download_";
    public static final String DOWNLOAD_FAILED = "download_failed";
    public static final String DOWNLOAD_LOCAL_FILENAME_FAILED = "download_local_filename_failed";
    public static final String DOWNLOAD_PREP_FAILED = "download_prep_failed";
    public static final String DOWNLOAD_QUEUE_OVERFLOW = "download_queue_overflow";
    public static final String DOWNLOAD_URI_MISSING = "download_uri_missing";
    public static final String ESIG = "esig_";
    public static final String ESIG_INVALID_SIGNATURE_FORMAT = "esig_invalid_signature_format";
    public static final String ESIG_MISSING_SIGNATURE = "esig_missing_signatures";
    public static final String INSTALL = "install_";
    public static final String INSTALL_APK_FAILED = "install_apk_failed";
    public static final String INSTALL_FILE_MOVE = "file_move";
    public static final String INSTALL_ILLEGAL_PUT_STATE = "install_put_state_non_final";
    public static final String INSTALL_RECOVERY_FAILURE = "install_recovert_failure";
    public static final String INSTALL_SHIBA_MITIGATION = "install_version_code_too_high";
    public static final String RETRY = "retry_";
    public static final String RETRY_NO_ID = "retry_no_retry_id";
    public static final String SERVICE = "service_";
    public static final String SERVICE_FUTURE_OVERRIDE = "service_future_override";
    public static final String SERVICE_ILLEGAL_FUTURE = "service_illegal_future";
    public static final String SERVICE_MISSING_ACTION = "service_missing_update_id";
    public static final String SERVICE_MISSING_INTENT = "service_missing_update_id";
    public static final String SERVICE_MISSING_UPDATE_ID = "service_missing_update_id";
    public static final String SERVICE_UNEXPECTED_EXCEPTION = "service_unexpected_exception";
    public static final String SERVICE_UPDATE_NOT_FOUND = "service_update_not_found";
    public static final String UNINSTALL_FAILED = "uninstall_failed";
    public static final String UTIL = "util_";
    public static final String UTIL_EXTRA_NO_UPDATE_ID = "util_extra_no_update_id";
    public static final String UTIL_INTENT_NO_UPDATE_ID = "util_intent_no_update_id";
    public static final String UTIL_UPDATEINFO_NOT_FOUND_IN_STORAGE = "util_update_not_found_storage";
    public static final String VERIFICATION = "verification_";
    public static final String VERIFICATION_BASE_VERSION_CHANGED = "verification_base_version_changed";
    public static final String VERIFICATION_CHECKSUM_FAILED = "verification_checksum_failed";
    public static final String VERIFICATION_FAILED = "verification_failed";
    public static final String VERIFICATION_METADATA_FAILED = "verification_metadata_failed";
    public static final String VERIFICATION_SIGNATURE_FAILED = "verification_signature_failed";
}
