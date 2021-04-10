package com.oculus.appmanager.info;

import X.AnonymousClass006;
import com.oculus.appmanager.installer.analytics.InstallerAnalytics;
import com.oculus.downloader.extras.contract.ExtrasDatabase;
import com.oculus.horizon.linkedaccounts.database.contract.LinkedAccountsSQLiteContract;
import java.util.Comparator;

public class ApkUpdateInfoContract {
    public static final String EXTRA_ASSET_REQUIRED_FILENAME = "asset_req_filename";
    public static final String EXTRA_CANCELED_BY = "canceled_by";
    public static final String EXTRA_CREATED_TIMESTAMP = "created_ts";
    public static final String EXTRA_DEPENDENT_START_BY = "dependency_started_by";
    public static final String EXTRA_DOWNLOADED_EXTERNAL_FILE = "download_local_file";
    public static final String EXTRA_DOWNLOADED_SIZE = "download_size";
    public static final String EXTRA_DOWNLOADING_EXTERNAL_FILE = "downloading_external_file";
    public static final String EXTRA_DOWNLOAD_ID = "download_id";
    public static final String EXTRA_DOWNLOAD_NOTIF_SENT = "download_success_notification_sent";
    public static final String EXTRA_DOWNLOAD_PENDING_DURATION_SECONDS = "download_pending_duration";
    public static final String EXTRA_DOWNLOAD_REASON = "download_reason";
    public static final String EXTRA_DOWNLOAD_RUNNING_DURATION_SECONDS = "download_running_duration";
    public static final String EXTRA_DOWNLOAD_SCHEDULED_TIME_MS = "download_scheduled_time";
    public static final String EXTRA_DOWNLOAD_START_TIME_MS = "download_start_time";
    public static final String EXTRA_DOWNLOAD_STATUS = "download_status";
    public static final String EXTRA_ERROR_CODE = "error_code";
    public static final String EXTRA_EXTERNAL_PATCHED_FILE = "patch_external_file";
    public static final String EXTRA_FAILED_CATEGORY = "failed_category";
    public static final String EXTRA_FAILED_DESCR = "failed_descr";
    public static final String EXTRA_FAILED_STATE = "failed_state";
    public static final String EXTRA_FAILURE_CODE = "failure_code";
    public static final String EXTRA_FUTURE_ID = "future_id";
    public static final String EXTRA_FUTURE_PID = "future_pid";
    public static final String EXTRA_INSTALLATION_DURATION_MS = "install_duration_ms";
    public static final String EXTRA_INSTALLATION_START_TIME_MS = "install_start_time_ms";
    public static final String EXTRA_INSTALL_BACKUP_PATH = "install_backup_path";
    public static final String EXTRA_INSTALL_PREP_PATH = "install_preparation_path";
    public static final String EXTRA_IS_REQUIRED_ASSET = "is_required_asset";
    public static final String EXTRA_IS_RETRY_OF = "is_retry_of";
    public static final String EXTRA_PACKAGE_NAME = "package_name";
    public static final String EXTRA_PARSED_VERSION_CODE = "parsed_version_code";
    public static final String EXTRA_PATCH_DURATION_MS = "patch_duration_ms";
    public static final String EXTRA_PATCH_FAILURE_FALLBACK_CHECKSUM = "patch_failure_fallback_checksum";
    public static final String EXTRA_PATCH_FAILURE_FALLBACK_SIZE = "patch_failure_fallback_size";
    public static final String EXTRA_PATCH_FAILURE_FALLBACK_URI = "patch_failure_fallback_url";
    public static final String EXTRA_PATCH_START_TIME_MS = "patch_start_time_ms";
    public static final String EXTRA_PERSISTENT_ASSET_NAMES = "persistent_asset_names";
    public static final String EXTRA_RECOVERY_TRIES = "recovery_tries";
    public static final String EXTRA_RETRIED_AS_ID = "retry_id";
    public static final String EXTRA_SANDBOXED_FILE = "sandboxed_local_file";
    public static final String EXTRA_STACK_TRACE = "stack_trace";
    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_STATE_TIMESTAMP = "state_ts";
    public static final String EXTRA_TRANSITIVE_FAILURE = "failed_transitive";
    public static final String EXTRA_UPDATE_PAYLOAD_SIZE = "payload_size";
    public static final String EXTRA_UUID = "uuid";
    public static final String EXTRA_VERIFICATION_DURATION_MS = "verification_duration_ms";
    public static final String EXTRA_VERIFICATION_START_TIME_MS = "verification_start_time_ms";
    public static final int INVALID_FAILURE_CODE = -1;
    public static final long INVALID_FUTURE_ID = -1;
    public static final int INVALID_FUTURE_PID = -1;
    public static final long INVALID_TIME = -1;
    public static final long INVALID_UPDATE_ID = -1;
    public static final int STATE_CANCELED = 262656;
    public static final int STATE_DELETING = 131184;
    public static final int STATE_DOWNLOADED = 655408;
    public static final int STATE_DOWNLOADING = 655392;
    public static final int STATE_FAILED = 262912;
    public static final int STATE_INSTALLING = 131168;
    public static final int STATE_NEW = 589825;
    public static final int STATE_QUEUED_DOWNLOAD = 655376;
    public static final int STATE_RETRIED = 263168;
    public static final int STATE_SUCCESS = 262400;
    public static final int STATE_TYPE_CANCELABLE = 524288;
    public static final int STATE_TYPE_FINAL = 262144;
    public static final int STATE_TYPE_INITIAL = 65536;
    public static final int STATE_TYPE_MASK = -65536;
    public static final int STATE_TYPE_TRANSIENT = 131072;
    public static final int STATE_UNINSTALLING = 132352;
    public static final int STATE_VERIFIED = 655440;
    public static final int STATE_VERIFYING = 655424;
    public static final long UNKNOWN_DOWNLOAD_SIZE = -1;
    public static final int UNKNOWN_VERSION = Integer.MIN_VALUE;
    public static final int UPDATE_DIRECT_APK = 1179648;
    public static final int UPDATE_DIRECT_ASSET = 1310720;
    public static final int UPDATE_DIRECT_OBB = 1114112;
    public static final int UPDATE_STORE_FULL_APK = 9568256;
    public static final int UPDATE_STORE_FULL_ASSET = 9699328;
    public static final int UPDATE_STORE_FULL_OBB = 9502720;
    public static final int UPDATE_STORE_IN_PLACE_FULL_APK = 26345472;
    public static final int UPDATE_STORE_IN_PLACE_FULL_ASSET = 26476544;
    public static final int UPDATE_STORE_IN_PLACE_FULL_OBB = 26279936;
    public static final int UPDATE_STORE_IN_PLACE_PATCH_APK = 27394048;
    public static final int UPDATE_STORE_IN_PLACE_PATCH_ASSET = 27525120;
    public static final int UPDATE_STORE_IN_PLACE_PATCH_OBB = 27328512;
    public static final int UPDATE_STORE_PATCH_APK = 10616832;
    public static final int UPDATE_STORE_PATCH_ASSET = 10747904;
    public static final int UPDATE_STORE_PATCH_OBB = 10551296;
    public static final int UPDATE_TYPE_APK = 131072;
    public static final int UPDATE_TYPE_ASSET = 262144;
    public static final int UPDATE_TYPE_FULL = 1048576;
    public static final int UPDATE_TYPE_IN_PLACE = 16777216;
    public static final int UPDATE_TYPE_MASK = -65536;
    public static final int UPDATE_TYPE_OBB = 65536;
    public static final int UPDATE_TYPE_PATCH = 2097152;
    public static final int UPDATE_TYPE_STORE = 8388608;
    public static final int UPDATE_TYPE_UNINSTALL = 4194304;
    public static final int UPDATE_UNINSTALL = 4194304;
    public static final int VERSION_NOT_INSTALLED = Integer.MIN_VALUE;

    public static class ApkUpdateTable {
        public static final String NAME = "apk_updates";

        public static class Columns {
            public static Column ACCESS_TOKEN = new Column("access_token", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column BASE_VERSION = new Column(InstallerAnalytics.Extras.BASE_VERSION, ExtrasDatabase.ColumnsTypes.INTEGER);
            @Deprecated
            public static Column BINARY_ID = new Column("binary_id", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column DEPENDENCIES = new Column("dependencies", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column DISPLAY_TITLE = new Column("display_title", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column DOWNLOAD_CHECKSUM = new Column("checksum_hash", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column DOWNLOAD_CHECKSUM_ALG = new Column("checksum_hash_alg", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column DOWNLOAD_SIZE = new Column(ApkUpdateInfoContract.EXTRA_DOWNLOADED_SIZE, ExtrasDatabase.ColumnsTypes.INTEGER);
            public static Column DOWNLOAD_URL = new Column("download_url", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column EXTERNAL_SIGNATURES = new Column("external_signatures", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column ID = new Column(LinkedAccountsSQLiteContract.Columns._ID, "INTEGER PRIMARY KEY AUTOINCREMENT");
            public static Column IDENTIFIER = new Column("package_name", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column IS_UPDATE = new Column(InstallerAnalytics.Extras.IS_UPDATE, ExtrasDatabase.ColumnsTypes.INTEGER);
            public static Column ITEM_ID = new Column("item_id", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column REQUESTING_PACKAGE = new Column("requesting_package", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column REQUEST_HEADERS = new Column("request_headers", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column REQUEST_ORIGIN = new Column(InstallerAnalytics.Extras.REQUEST_ORIGIN, ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column SIGNATURE = new Column("signature", ExtrasDatabase.ColumnsTypes.TEXT);
            public static Column TARGET_VERSION = new Column("version", ExtrasDatabase.ColumnsTypes.INTEGER);
            public static Column UPDATE_TYPE = new Column(InstallerAnalytics.Extras.UPDATE_TYPE, ExtrasDatabase.ColumnsTypes.INTEGER);
        }
    }

    public interface IntegerBasedEnum {
        int asInt();
    }

    public enum UpdateState implements IntegerBasedEnum, Comparator<UpdateState> {
        NEW(ApkUpdateInfoContract.STATE_NEW),
        QUEUED_DOWNLOAD(ApkUpdateInfoContract.STATE_QUEUED_DOWNLOAD),
        DOWNLOADING(ApkUpdateInfoContract.STATE_DOWNLOADING),
        DOWNLOADED(ApkUpdateInfoContract.STATE_DOWNLOADED),
        VERIFYING(ApkUpdateInfoContract.STATE_VERIFYING),
        VERIFIED(ApkUpdateInfoContract.STATE_VERIFIED),
        INSTALLING(ApkUpdateInfoContract.STATE_INSTALLING),
        DELETING(ApkUpdateInfoContract.STATE_DELETING),
        SUCCESS(ApkUpdateInfoContract.STATE_SUCCESS),
        FAILED(ApkUpdateInfoContract.STATE_FAILED),
        CANCELED(ApkUpdateInfoContract.STATE_CANCELED),
        RETRIED(ApkUpdateInfoContract.STATE_RETRIED),
        UNINSTALLING(ApkUpdateInfoContract.STATE_UNINSTALLING);
        
        public final int mCode;

        public static UpdateState fromInt(int i) {
            return (UpdateState) ApkUpdateInfoContract.A00(UpdateState.class, i);
        }

        public boolean isCancelable() {
            return isOfType(ApkUpdateInfoContract.STATE_TYPE_CANCELABLE);
        }

        public boolean isFinal() {
            return isOfType(262144);
        }

        public boolean isInitial() {
            return isOfType(65536);
        }

        public boolean isOfType(int i) {
            if ((i & this.mCode & -65536) != 0) {
                return true;
            }
            return false;
        }

        public boolean isTransient() {
            return isOfType(131072);
        }

        /* access modifiers changed from: public */
        UpdateState(int i) {
            this.mCode = i;
        }

        @Override // com.oculus.appmanager.info.ApkUpdateInfoContract.IntegerBasedEnum
        public int asInt() {
            return this.mCode;
        }

        public int compare(UpdateState updateState) {
            return compare(this, updateState);
        }

        public int compare(UpdateState updateState, UpdateState updateState2) {
            return (updateState2.asInt() & 65535) - (updateState.asInt() & 65535);
        }
    }

    public enum UpdateType implements IntegerBasedEnum {
        DIRECT_APK(ApkUpdateInfoContract.UPDATE_DIRECT_APK),
        DIRECT_OBB(ApkUpdateInfoContract.UPDATE_DIRECT_OBB),
        DIRECT_ASSET(ApkUpdateInfoContract.UPDATE_DIRECT_ASSET),
        STORE_FULL_APK(ApkUpdateInfoContract.UPDATE_STORE_FULL_APK),
        STORE_PATCH_APK(ApkUpdateInfoContract.UPDATE_STORE_PATCH_APK),
        STORE_FULL_OBB(ApkUpdateInfoContract.UPDATE_STORE_FULL_OBB),
        STORE_PATCH_OBB(ApkUpdateInfoContract.UPDATE_STORE_PATCH_OBB),
        STORE_FULL_ASSET(ApkUpdateInfoContract.UPDATE_STORE_FULL_ASSET),
        STORE_PATCH_ASSET(ApkUpdateInfoContract.UPDATE_STORE_PATCH_ASSET),
        UNINSTALL(4194304),
        STORE_IN_PLACE_FULL_APK(ApkUpdateInfoContract.UPDATE_STORE_IN_PLACE_FULL_APK),
        STORE_IN_PLACE_PATCH_APK(ApkUpdateInfoContract.UPDATE_STORE_IN_PLACE_PATCH_APK),
        STORE_IN_PLACE_FULL_OBB(ApkUpdateInfoContract.UPDATE_STORE_IN_PLACE_FULL_OBB),
        STORE_IN_PLACE_PATCH_OBB(ApkUpdateInfoContract.UPDATE_STORE_IN_PLACE_PATCH_OBB),
        STORE_IN_PLACE_FULL_ASSET(ApkUpdateInfoContract.UPDATE_STORE_IN_PLACE_FULL_ASSET),
        STORE_IN_PLACE_PATCH_ASSET(ApkUpdateInfoContract.UPDATE_STORE_IN_PLACE_PATCH_ASSET);
        
        public final int mCode;

        public static UpdateType fromInt(int i) {
            return (UpdateType) ApkUpdateInfoContract.A00(UpdateType.class, i);
        }

        public boolean isApk() {
            return isOfType(131072);
        }

        public boolean isAsset() {
            return isOfType(262144);
        }

        public boolean isFromStore() {
            return isOfType(ApkUpdateInfoContract.UPDATE_TYPE_STORE);
        }

        public boolean isFullUpdate() {
            return isOfType(ApkUpdateInfoContract.UPDATE_TYPE_FULL);
        }

        public boolean isInPlace() {
            return isOfType(16777216);
        }

        public boolean isObb() {
            return isOfType(65536);
        }

        public boolean isOfType(int i) {
            if ((i & this.mCode & -65536) != 0) {
                return true;
            }
            return false;
        }

        public boolean isPatchUpdate() {
            return isOfType(ApkUpdateInfoContract.UPDATE_TYPE_PATCH);
        }

        public UpdateType toFullUpdate() {
            if (isOfType(ApkUpdateInfoContract.UPDATE_TYPE_PATCH)) {
                return fromInt(asInt() ^ 3145728);
            }
            return this;
        }

        /* access modifiers changed from: public */
        UpdateType(int i) {
            this.mCode = i;
        }

        @Override // com.oculus.appmanager.info.ApkUpdateInfoContract.IntegerBasedEnum
        public int asInt() {
            return this.mCode;
        }
    }

    public static class Column {
        public final String name;
        public final String type;

        public Column(String str, String str2) {
            this.name = str;
            this.type = str2;
        }
    }

    public static <T extends Enum & IntegerBasedEnum> T A00(Class<T> cls, int i) {
        T[] enumConstants = cls.getEnumConstants();
        for (T t : enumConstants) {
            if (t.asInt() == i) {
                return t;
            }
        }
        throw new IllegalArgumentException(AnonymousClass006.A06(cls.getSimpleName(), " int : ", i));
    }
}
