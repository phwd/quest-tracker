package com.oculus.appmanager.installer.analytics;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.debug.DebugMode;
import com.oculus.logging.utils.EventManager;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID"})
@ApplicationScoped
public class InstallerAnalytics {
    public static final String ACTION_APPMANAGER_VR_ENABLE_FAILURE = "appmanager_enable_vr_failure";
    public static final String ACTION_CANCELED = "cancelled";
    public static final String ACTION_CONSISTENCY_DOWNLOADED_TIMEOUT = "downloaded_timeout";
    public static final String ACTION_CONSISTENCY_DOWNLOAD_TIMEOUT = "download_timeout";
    public static final String ACTION_CONSISTENCY_INSTALL_TIMEOUT = "install_timeout";
    public static final String ACTION_CONSISTENCY_VERIFIED_TIMEOUT = "verified_timeout";
    public static final String ACTION_CONSISTENCY_VERIFYING_TIMEOUT = "verifying_timeout";
    public static final String ACTION_DOWNLOAD = "download";
    public static final String ACTION_DOWNLOADED_FILE_NOT_FOUND = "downloaded_file_not_found";
    public static final String ACTION_DOWNLOADMANAGER_DISABLED = "downloadmanager_disabled";
    public static final String ACTION_DOWNLOAD_CONFIG_FAILED = "download_config_failed";
    public static final String ACTION_DOWNLOAD_FAILURE = "download_failure";
    public static final String ACTION_DOWNLOAD_FAILURE_CANNOT_RESUME = "download_fail_cant_resume";
    public static final String ACTION_DOWNLOAD_FAILURE_CANT_START_INSTALLER_SERVICE = "download_fail_cant_start_installer";
    public static final String ACTION_DOWNLOAD_FAILURE_DOWNLOAD_CANCELED = "download_fail_user_canceled";
    public static final String ACTION_DOWNLOAD_FAILURE_DOWNLOAD_INFO_GENERATION = "download_fail_downloadinfo_generation";
    public static final String ACTION_DOWNLOAD_FAILURE_FILE_ERROR = "download_fail_file_error";
    public static final String ACTION_DOWNLOAD_FAILURE_FILE_EXISTS = "download_fail_file_exists";
    public static final String ACTION_DOWNLOAD_FAILURE_HTTP_ERROR = "download_fail_http_error";
    public static final String ACTION_DOWNLOAD_FAILURE_INSUFFICIENT_SPACE = "download_fail_insufficient_space";
    public static final String ACTION_DOWNLOAD_FAILURE_IO_EXCEPTION_DURING_RENAME = "download_fail_rename_io_exception";
    public static final String ACTION_DOWNLOAD_FAILURE_NO_EXTERNAL_STORAGE = "download_fail_no_external_storage";
    public static final String ACTION_DOWNLOAD_FAILURE_TOO_MANY_REDIRECTS = "download_fail_too_many_redirects";
    public static final String ACTION_DOWNLOAD_FAILURE_UNHANDLED_HTTP = "download_fail_unhandled_http";
    public static final String ACTION_DOWNLOAD_FAILURE_UNKNOWN = "download_fail_unknown";
    public static final String ACTION_DOWNLOAD_FAILURE_VR_SIGN = "download_fail_not_vr_signed";
    public static final String ACTION_DOWNLOAD_PREP_FAILURE = "download_prep_failure";
    public static final String ACTION_INSTALL = "install";
    public static final String ACTION_INSTALL_ASSET = "asset_install";
    public static final String ACTION_INSTALL_FAILURE = "install_failure";
    public static final String ACTION_INSTALL_SUCCESS = "install_success";
    public static final String ACTION_PACKAGE_CHANGE_BEFORE_VERIFICATION = "update_fail_base_version_changed";
    public static final String ACTION_REQUEST_ORIGIN = "request_origin_";
    public static final String ACTION_SIGNATURE_VERIFICATION_FAILURE = "verify_signature_failure";
    public static final String ACTION_UNINSTALL = "uninstall";
    public static final String ACTION_UNINSTALL_FAILURE = "uninstall_failure";
    public static final String ACTION_UNINSTALL_SUCCESS = "uninstall_success";
    public static final String ACTION_VERIFICATION = "verify";
    public static final String ACTION_VERIFICATION_FAILURE = "verify_failure";
    public static final String EVENT_BOOT_CLEAN_LINGERING_FILES = "moonlight_boot_clean_lingering_files";
    public static final String EVENT_CONSISTENCY_CHECK_STARTED = "moonlight_consistency_check_started";
    public static final String EVENT_DOWNLOAD_CANCELLED = "moonlight_download_cancelled";
    public static final String EVENT_DOWNLOAD_FAILED = "moonlight_download_failed";
    public static final String EVENT_DOWNLOAD_QUEUED = "moonlight_download_queued";
    public static final String EVENT_DOWNLOAD_SCHEDULED = "moonlight_download_scheduled";
    public static final String EVENT_DOWNLOAD_SUCCEEDED = "moonlight_download_succeeded";
    public static final String EVENT_FINAL_UPDATE_STATE = "moonlight_final_update_state";
    public static final String EVENT_INSTALLER_DELETE_FAILED = "moonlight_installer_delete_failed";
    public static final String EVENT_INSTALL_ASSET_REQUESTED = "moonlight_install_asset_request";
    public static final String EVENT_INSTALL_ASSET_STARTED = "moonlight_install_asset_started";
    public static final String EVENT_INSTALL_ASSET_SUCCEEDED = "moonlight_install_asset_succeeded";
    public static final String EVENT_INSTALL_CANCELLED = "moonlight_install_cancelled";
    public static final String EVENT_INSTALL_FAILED = "moonlight_install_failed";
    public static final String EVENT_INSTALL_RECOVERY_FAILURE = "moonlight_recovery_failure";
    public static final String EVENT_INSTALL_REQUESTED = "moonlight_install_request";
    public static final String EVENT_INSTALL_RETRIED = "moonlight_install_retried";
    public static final String EVENT_INSTALL_STARTED = "moonlight_install_started";
    public static final String EVENT_INSTALL_SUCCEEDED = "moonlight_install_succeeded";
    public static final String EVENT_SIGNATURE_VERIFICATION_FAILED = "moonlight_signature_verification_failed";
    public static final String EVENT_UNINSTALL_FAILED = "moonlight_uninstall_failed";
    public static final String EVENT_UNINSTALL_STARTED = "moonlight_uninstall_started";
    public static final String EVENT_UNINSTALL_SUCCEEDED = "moonlight_uninstall_succeeded";
    public static final String EVENT_VERIFICATION_FAILED = "moonlight_verification_failed";
    public static final String EVENT_VERIFICATION_SUCCEEDED = "moonlight_verification_succeeded";
    public static final String EXTRA_ACTUAL_VERSION = "actual_version";
    public static final String EXTRA_EXPECTED_SIGNATURE = "expected_signature";
    public static final String EXTRA_EXPECTED_VERSION = "expected_version";
    public static final String EXTRA_PACKAGE_NAME = "package_name";
    public static final String INSTALL_FUNNEL_NAME = "OC_INSTALL_FUNNEL";
    public static final String TAG = "InstallerAnalytics";
    public static volatile InstallerAnalytics _UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final DebugMode mDebugMode;
    @Inject
    @Eager
    public final InfoUtils mInfoUtils;

    public static abstract class Extras {
        public static final String APK_PAYLOAD_SIZE = "apk_payload_size";
        public static final String APK_SIZE = "apk_size";
        public static final String APP_FROM_VERSION_CODE = "app_from_version_code";
        public static final String ASSETS_NAMES = "assets_names";
        public static final String ASSETS_PRESENT = "assets_present";
        public static final String ASSETS_REQUIRED = "assets_required";
        public static final String ASSETS_SIZE = "assets_size";
        public static final String BASE_VERSION = "base_version";
        public static final String DEPENDENCIES = "dependencies_count";
        public static final String DOWNLOAD_CONNECTION_IS_ROAMING = "download_connection_is_roaming";
        public static final String DOWNLOAD_CONNECTION_SPEED = "download_connection_speed";
        public static final String DOWNLOAD_CONNECTION_TYPE = "download_connection_type";
        public static final String DOWNLOAD_DURTATION_MS = "download_duration_ms";
        public static final String DOWNLOAD_ID = "download_id";
        public static final String DOWNLOAD_PENDING_DURATION_SECONDS = "download_pending_duration_s";
        public static final String DOWNLOAD_REASON = "download_reason";
        public static final String DOWNLOAD_RUNNING_DURATION_SECONDS = "download_running_duration_s";
        public static final String DOWNLOAD_SCHEDULE_TIME_MS = "download_schedule_time_ms";
        public static final String DOWNLOAD_START_TIME_MS = "download_start_time_ms";
        public static final String DOWNLOAD_STATUS = "download_status";
        public static final String DOWNLOAD_URI = "download_uri";
        public static final String DURATION_MS = "duration_ms";
        public static final String ERROR_CODE = "error_code";
        public static final String EXPECTED_SIGNATURE = "expected_signature";
        public static final String FAILED_CATEGORY = "failed_category";
        public static final String FAILED_DESCRIPTION = "failed_description";
        public static final String FAILED_STATE = "failed_state";
        public static final String FAILURE_CODE = "failure_code";
        public static final String FILES_LIST = "file_list";
        public static final String FILE_PATH = "file_path";
        public static final String FOUND_SIGNATURE = "found_signature";
        public static final String HAS_PARENT = "has_parent_update";
        public static final String IDENTIFIER = "install_identifier";
        public static final String INSTALL_RECOVERY_EXCEPTION = "recovery_exception";
        public static final String INSTALL_TYPE = "install_type";
        public static final String IS_RETRY = "is_retry";
        public static final String IS_TRANSITIVE_FAILURE = "is_transitive_failure";
        public static final String IS_UPDATE = "is_update";
        public static final String ITEM_ID = "item_id";
        public static final String OBB_PRESENT = "obb_present";
        public static final String OBB_SIZE = "obb_size";
        public static final String PACKAGE_NAME = "package_name";
        public static final String PATCHING_DURATION_MS = "patching_duration_ms";
        public static final String PATH = "path";
        public static final String REQUESTING_PKG = "requesting_package";
        public static final String REQUEST_ORIGIN = "request_origin";
        public static final String STACK_TRACE = "stack_trace";
        public static final String TARGET_VERSION = "target_version";
        public static final String TOTAL_DOWNLOAD_SIZE = "total_download_size_bytes";
        public static final String TOTAL_PAYLOAD_SIZE = "total_payload_size_bytes";
        public static final String UPDATE_STATE = "state";
        public static final String UPDATE_STATE_TIMESTAMP = "state_timestamp";
        public static final String UPDATE_TYPE = "update_type";
        public static final String UUID = "uuid";
        public static final String VERIFICATION_DURATION_MS = "verification_duration_ms";
    }

    public enum InstallType {
        FULL,
        PATCH,
        MIXED
    }

    /* renamed from: com.oculus.appmanager.installer.analytics.InstallerAnalytics$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$appmanager$info$ApkUpdateInfoContract$UpdateState;

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|(3:23|24|26)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0070 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
            // Method dump skipped, instructions count: 123
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.appmanager.installer.analytics.InstallerAnalytics.AnonymousClass1.<clinit>():void");
        }
    }

    @Inject
    public InstallerAnalytics(AbstractC06640p5 r6) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r6);
        this.mInfoUtils = (InfoUtils) AnonymousClass117.A00(567, r6);
        this.mDebugMode = DebugMode.A00(r6);
        ((EventManager) AnonymousClass0J2.A03(2, 242, this._UL_mInjectionContext)).A84(INSTALL_FUNNEL_NAME, (int) TimeUnit.DAYS.toSeconds(1));
    }
}
