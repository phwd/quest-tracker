package com.oculus.appmanager.info;

import android.text.TextUtils;
import com.facebook.inject.Assisted;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.info.database.ApkUpdateExtrasManager;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.installer.analytics.InstallerAnalytics;
import com.oculus.appmanager.installer.contract.errors.ErrorCategories;
import com.oculus.debug.DebugMode;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.extras.Extras;
import com.oculus.extras.ExtrasBuilder;
import com.oculus.library.database.contract.LibraryDBContract;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoDispatcher_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateExtrasManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class ApkUpdateInfo {
    private static final long INVALID_FUTURE_ID = -1;
    private static final int INVALID_FUTURE_PID = -1;
    private static final long INVALID_UPDATE_ID = -1;
    public static final String TAG = "com.oculus.appmanager.info.ApkUpdateInfo";
    @Nullable
    public final String accessToken;
    public final long baseVersion;
    @Nullable
    public final String checksumHash;
    @Nullable
    public final String checksumHashAlg;
    final ImmutableList<Long> dependencies;
    @Nullable
    public final String displayTitle;
    public final long downloadSize;
    @Nullable
    public final String downloadUrl;
    @Nullable
    public final String expectedSignature;
    @Nullable
    public final String externalSignatures;
    public final long id;
    public final String identifier;
    public final boolean isUpdate;
    private final ApkUpdateExtrasManager mApkUpdateExtrasManager;
    private final ApkUpdateInfoDispatcher mApkUpdateInfoDispatcher;
    private final DebugMode mDebugMode;
    private final AtomicBoolean mDeleted = new AtomicBoolean(false);
    private final IErrorReporter mErrorReporter;
    private ApkUpdateExtras mExtras;
    @Nullable
    public final Map<String, String> requestHeaders;
    public final RequestOrigin requestOrigin;
    @Nullable
    public final String requestingPackage;
    @Nullable
    public final String storeItemId;
    public final long targetVersion;
    public final ApkUpdateInfoContract.UpdateType updateType;

    @AutoGeneratedAccessMethod
    public static final ApkUpdateInfoProvider _UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoProvider_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ApkUpdateInfoProvider) UL.factorymap.get(InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoProvider_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public ApkUpdateInfo(@Assisted long j, @Assisted String str, @Assisted ApkUpdateInfoContract.UpdateType updateType2, @Assisted long j2, @Assisted long j3, @Assisted boolean z, @Assisted long j4, @Assisted @Nullable String str2, @Assisted RequestOrigin requestOrigin2, @Assisted ImmutableList<Long> immutableList, @Assisted @Nullable String str3, @Assisted @Nullable String str4, @Assisted @Nullable String str5, @Assisted @Nullable String str6, @Assisted @Nullable String str7, @Assisted @Nullable String str8, @Assisted @Nullable String str9, @Assisted @Nullable String str10, @Assisted Extras extras, @Assisted @Nullable Map<String, String> map, ApkUpdateInfoDispatcher apkUpdateInfoDispatcher, ApkUpdateExtrasManager apkUpdateExtrasManager, DebugMode debugMode, IErrorReporter iErrorReporter) {
        this.id = j;
        this.identifier = str;
        this.updateType = updateType2;
        this.baseVersion = j2;
        this.targetVersion = j3;
        this.isUpdate = z;
        this.downloadSize = j4;
        this.downloadUrl = str2;
        this.requestOrigin = requestOrigin2;
        this.dependencies = immutableList;
        this.accessToken = str3;
        this.storeItemId = str4;
        this.checksumHash = str5;
        this.checksumHashAlg = str6;
        this.displayTitle = str7;
        this.requestingPackage = str8;
        this.expectedSignature = str9;
        this.externalSignatures = str10;
        this.requestHeaders = map;
        this.mApkUpdateInfoDispatcher = apkUpdateInfoDispatcher;
        this.mApkUpdateExtrasManager = apkUpdateExtrasManager;
        this.mDebugMode = debugMode;
        this.mErrorReporter = iErrorReporter;
        this.mExtras = new ApkUpdateExtras(extras);
    }

    public ApkUpdateInfoContract.UpdateState getState() {
        return getExtras().getState();
    }

    @Nullable
    public String getPackageName() {
        return getExtras().getPackageName();
    }

    public int getDependencyCount() {
        return this.dependencies.size();
    }

    public boolean isDeleted() {
        return this.mDeleted.get();
    }

    public void markDeleted() {
        this.mDeleted.set(true);
    }

    public synchronized ApkUpdateExtras getExtras() {
        return this.mExtras;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void setExtras(ApkUpdateExtras apkUpdateExtras) {
        this.mExtras = apkUpdateExtras;
    }

    public UpdateExtrasEditor edit() {
        return new UpdateExtrasEditor();
    }

    public synchronized String toString() {
        MoreObjects.ToStringHelper stringHelper;
        ApkUpdateExtras extras = getExtras();
        stringHelper = MoreObjects.toStringHelper((Class<?>) ApkUpdateInfo.class);
        stringHelper.add("id", this.id);
        stringHelper.add("identifier", this.identifier);
        stringHelper.add(InstallerAnalytics.Extras.UPDATE_TYPE, this.updateType.name());
        stringHelper.add("state_str", getState().name());
        stringHelper.add("dependencies", this.dependencies.isEmpty() ? "none" : TextUtils.join(",", this.dependencies));
        if (extras.hasKey("download_id")) {
            stringHelper.add("download_id", extras.getDownloadId());
        }
        if (extras.hasKey("failed_category")) {
            stringHelper.add("failed_category", extras.getFailedCategory());
        }
        if (extras.hasKey(ApkUpdateInfoContract.EXTRA_FAILED_DESCR)) {
            stringHelper.add(ApkUpdateInfoContract.EXTRA_FAILED_DESCR, extras.getFailedCategory());
        }
        if (extras.hasKey("failed_state")) {
            stringHelper.add("failed_state", extras.getFailedState().name());
        }
        if (extras.hasKey(ApkUpdateInfoContract.EXTRA_IS_RETRY_OF)) {
            stringHelper.add(ApkUpdateInfoContract.EXTRA_IS_RETRY_OF, extras.getRetryOf());
        }
        if (extras.hasKey(ApkUpdateInfoContract.EXTRA_RETRIED_AS_ID)) {
            stringHelper.add(ApkUpdateInfoContract.EXTRA_RETRIED_AS_ID, extras.getRetriedUpdate());
        }
        return stringHelper.toString();
    }

    public static class ApkUpdateExtras extends Extras {
        public ApkUpdateExtras(Extras extras) {
            super(extras.getData());
        }

        ApkUpdateExtras(Map<String, String> map) {
            super(map);
        }

        public long getCreatedTimestamp() {
            return getLong(ApkUpdateInfoContract.EXTRA_CREATED_TIMESTAMP, -1);
        }

        public ApkUpdateInfoContract.UpdateState getState() {
            return ApkUpdateInfoContract.UpdateState.fromInt(getInt("state", ApkUpdateInfoContract.UpdateState.NEW.asInt()));
        }

        public long getStateTimestamp() {
            return getLong(ApkUpdateInfoContract.EXTRA_STATE_TIMESTAMP, -1);
        }

        public String getPackageName() {
            return getString("package_name", null);
        }

        public long getParsedVersionCode() {
            return getLong(ApkUpdateInfoContract.EXTRA_PARSED_VERSION_CODE, LibraryDBContract.VERSION_NOT_INSTALLED);
        }

        public long getDependentStartedBy() {
            return getLong(ApkUpdateInfoContract.EXTRA_DEPENDENT_START_BY, -1);
        }

        public Set<String> getPersistentAssetNames() {
            return new HashSet(Arrays.asList(getString(ApkUpdateInfoContract.EXTRA_PERSISTENT_ASSET_NAMES, "").split(":::")));
        }

        public long getDownloadId() {
            return getLong("download_id", 0);
        }

        public String getDownloadingExternalFile() {
            return getString(ApkUpdateInfoContract.EXTRA_DOWNLOADING_EXTERNAL_FILE, null);
        }

        public String getDownloadedExternalFile() {
            return getString(ApkUpdateInfoContract.EXTRA_DOWNLOADED_EXTERNAL_FILE, null);
        }

        public int getDownloadStatus() {
            return getInt("download_status", 0);
        }

        public int getDownloadReason() {
            return getInt("download_reason", 0);
        }

        public long getDownloadedSize() {
            return getLong(ApkUpdateInfoContract.EXTRA_DOWNLOADED_SIZE, 0);
        }

        public long getDownloadPendingDurationSec() {
            return getLong(ApkUpdateInfoContract.EXTRA_DOWNLOAD_PENDING_DURATION_SECONDS, -1);
        }

        public long getDownloadRunningDurationSec() {
            return getLong(ApkUpdateInfoContract.EXTRA_DOWNLOAD_RUNNING_DURATION_SECONDS, -1);
        }

        public long getDownloadScheduledTime() {
            return getLong(ApkUpdateInfoContract.EXTRA_DOWNLOAD_SCHEDULED_TIME_MS, -1);
        }

        public long getDownloadStartTime() {
            return getLong(ApkUpdateInfoContract.EXTRA_DOWNLOAD_START_TIME_MS, -1);
        }

        public boolean getIsDownloadNotificationSent() {
            return getBoolean(ApkUpdateInfoContract.EXTRA_DOWNLOAD_NOTIF_SENT, false);
        }

        public long getUpdatePayloadSize() {
            return getLong(ApkUpdateInfoContract.EXTRA_UPDATE_PAYLOAD_SIZE, 0);
        }

        public String getFinalPatchedExternalFilePath() {
            return getString(ApkUpdateInfoContract.EXTRA_EXTERNAL_PATCHED_FILE, null);
        }

        public String getSandboxedFilePath() {
            return getString(ApkUpdateInfoContract.EXTRA_SANDBOXED_FILE, null);
        }

        public String getPatchFailureFallbackDownloadUri() {
            return getString(ApkUpdateInfoContract.EXTRA_PATCH_FAILURE_FALLBACK_URI, null);
        }

        public long getPatchFailureFallbackDownloadSize() {
            return getLong(ApkUpdateInfoContract.EXTRA_PATCH_FAILURE_FALLBACK_SIZE, 0);
        }

        public String getPatchFailureFallbackDownloadChecksum() {
            return getString(ApkUpdateInfoContract.EXTRA_PATCH_FAILURE_FALLBACK_CHECKSUM, null);
        }

        public long getVerificationStartTimeMs() {
            return getLong(ApkUpdateInfoContract.EXTRA_VERIFICATION_START_TIME_MS, -1);
        }

        public long getVerificationDurationMs() {
            return getLong(ApkUpdateInfoContract.EXTRA_VERIFICATION_DURATION_MS, -1);
        }

        public long getPatchingStartTimeMs() {
            return getLong(ApkUpdateInfoContract.EXTRA_PATCH_START_TIME_MS, -1);
        }

        public long getPatchingDurationMs() {
            return getLong(ApkUpdateInfoContract.EXTRA_PATCH_DURATION_MS, -1);
        }

        public long getInstallationStartTimeMs() {
            return getLong(ApkUpdateInfoContract.EXTRA_INSTALLATION_START_TIME_MS, -1);
        }

        public long getInstallationDurationMs() {
            return getLong(ApkUpdateInfoContract.EXTRA_INSTALLATION_DURATION_MS, -1);
        }

        public boolean isRequiredAsset() {
            return getBoolean(ApkUpdateInfoContract.EXTRA_IS_REQUIRED_ASSET, false);
        }

        public String getAssetRequiredFilename() {
            return getString(ApkUpdateInfoContract.EXTRA_ASSET_REQUIRED_FILENAME, null);
        }

        public String getPreparedInstallFilePath() {
            return getString(ApkUpdateInfoContract.EXTRA_INSTALL_PREP_PATH, null);
        }

        public String getInstallBackupFilePath() {
            return getString(ApkUpdateInfoContract.EXTRA_INSTALL_BACKUP_PATH, null);
        }

        public ApkUpdateInfoContract.UpdateState getFailedState() {
            return ApkUpdateInfoContract.UpdateState.fromInt(getInt("failed_state", ApkUpdateInfoContract.UpdateState.NEW.asInt()));
        }

        public String getFailedCategory() {
            return getString("failed_category", null);
        }

        public String getFailedDescription() {
            return getString(ApkUpdateInfoContract.EXTRA_FAILED_DESCR, null);
        }

        public int getFailureCode() {
            return getInt("failure_code", -1);
        }

        public boolean isTransitiveFailure() {
            return getBoolean(ApkUpdateInfoContract.EXTRA_TRANSITIVE_FAILURE, false);
        }

        public int getErrorCode() {
            return getInt("error_code", 0);
        }

        public String getStackTrace() {
            return getString("stack_trace", null);
        }

        public long getCancelledBy() {
            return getLong(ApkUpdateInfoContract.EXTRA_CANCELED_BY, -1);
        }

        public long getRetryOf() {
            return getLong(ApkUpdateInfoContract.EXTRA_IS_RETRY_OF, -1);
        }

        public long getRetriedUpdate() {
            return getLong(ApkUpdateInfoContract.EXTRA_RETRIED_AS_ID, -1);
        }

        public int getRecoveryTries() {
            return getInt(ApkUpdateInfoContract.EXTRA_RECOVERY_TRIES, 0);
        }

        public String getUUID() {
            return getString("uuid", null);
        }

        public long getFutureId() {
            return getLong(ApkUpdateInfoContract.EXTRA_FUTURE_ID, -1);
        }

        public int getFuturePid() {
            return getInt(ApkUpdateInfoContract.EXTRA_FUTURE_PID, -1);
        }

        @Override // com.oculus.extras.Extras
        public String toString() {
            MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) ApkUpdateExtras.class);
            stringHelper.add("data", getData());
            return stringHelper.toString();
        }
    }

    public static class ApkUpdateExtrasBuilder extends ExtrasBuilder {
        public ApkUpdateExtrasBuilder() {
        }

        public ApkUpdateExtrasBuilder(Extras extras) {
            putExtras(extras);
        }

        public ApkUpdateExtrasBuilder putCreatedTimestamp(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_CREATED_TIMESTAMP, j);
            return this;
        }

        private ApkUpdateExtrasBuilder putState(ApkUpdateInfoContract.UpdateState updateState) {
            putInt("state", updateState.asInt());
            return this;
        }

        private ApkUpdateExtrasBuilder putStateTimestamp(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_STATE_TIMESTAMP, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putState(ApkUpdateInfoContract.UpdateState updateState, long j) {
            putState(updateState);
            putStateTimestamp(j);
            return this;
        }

        public ApkUpdateExtrasBuilder putPackageName(String str) {
            putString("package_name", str);
            return this;
        }

        public ApkUpdateExtrasBuilder putParsedVersionCode(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_PARSED_VERSION_CODE, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putDependentStartedBy(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_DEPENDENT_START_BY, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putPersistentAssetNames(Set<String> set) {
            putString(ApkUpdateInfoContract.EXTRA_PERSISTENT_ASSET_NAMES, TextUtils.join(":::", set));
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadId(long j) {
            putLong("download_id", j);
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadingExternalFile(String str) {
            putString(ApkUpdateInfoContract.EXTRA_DOWNLOADING_EXTERNAL_FILE, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadedExternalFile(String str) {
            putString(ApkUpdateInfoContract.EXTRA_DOWNLOADED_EXTERNAL_FILE, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadStatus(int i) {
            putInt("download_status", i);
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadReason(int i) {
            putInt("download_reason", i);
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadedSize(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_DOWNLOADED_SIZE, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadPendingDurationSec(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_DOWNLOAD_PENDING_DURATION_SECONDS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadRunningDurationSec(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_DOWNLOAD_RUNNING_DURATION_SECONDS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadScheduledTime(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_DOWNLOAD_SCHEDULED_TIME_MS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putDownloadStartTime(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_DOWNLOAD_START_TIME_MS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putIsDownloadNotificationSent(boolean z) {
            putBoolean(ApkUpdateInfoContract.EXTRA_DOWNLOAD_NOTIF_SENT, z);
            return this;
        }

        public ApkUpdateExtrasBuilder putUpdatePayloadSize(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_UPDATE_PAYLOAD_SIZE, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putFinalPatchedExternalFile(String str) {
            putString(ApkUpdateInfoContract.EXTRA_EXTERNAL_PATCHED_FILE, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putSandboxedFile(String str) {
            putString(ApkUpdateInfoContract.EXTRA_SANDBOXED_FILE, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putPatchFailureFallbackDownloadUri(String str) {
            putString(ApkUpdateInfoContract.EXTRA_PATCH_FAILURE_FALLBACK_URI, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putPatchFailureFallbackSize(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_PATCH_FAILURE_FALLBACK_SIZE, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putPatchFailureFallbackChecksum(String str) {
            putString(ApkUpdateInfoContract.EXTRA_PATCH_FAILURE_FALLBACK_CHECKSUM, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putVerificationStartTimeMs(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_VERIFICATION_START_TIME_MS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putVerificationDurationMs(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_VERIFICATION_DURATION_MS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putPatchingStartTimeMs(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_PATCH_START_TIME_MS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putPatchingDurationMs(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_PATCH_DURATION_MS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putInstallationStartTimeMs(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_INSTALLATION_START_TIME_MS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putInstallationDurationMs(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_INSTALLATION_DURATION_MS, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putIsRequiredAsset(boolean z) {
            putBoolean(ApkUpdateInfoContract.EXTRA_IS_REQUIRED_ASSET, z);
            return this;
        }

        public ApkUpdateExtrasBuilder putAssetRequiredFilename(String str) {
            putString(ApkUpdateInfoContract.EXTRA_ASSET_REQUIRED_FILENAME, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putPreparedInstallFilePath(String str) {
            putString(ApkUpdateInfoContract.EXTRA_INSTALL_PREP_PATH, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putInstallBackupFilePath(String str) {
            putString(ApkUpdateInfoContract.EXTRA_INSTALL_BACKUP_PATH, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putFailedState(ApkUpdateInfoContract.UpdateState updateState) {
            putInt("failed_state", updateState.asInt());
            return this;
        }

        public ApkUpdateExtrasBuilder putFailedCategory(String str) {
            putString("failed_category", str);
            return this;
        }

        public ApkUpdateExtrasBuilder putFailedDescription(String str) {
            putString(ApkUpdateInfoContract.EXTRA_FAILED_DESCR, str);
            return this;
        }

        public ApkUpdateExtrasBuilder putFailureCode(int i) {
            putInt("failure_code", i);
            return this;
        }

        public ApkUpdateExtrasBuilder putIsTransitiveFailure(boolean z) {
            putBoolean(ApkUpdateInfoContract.EXTRA_TRANSITIVE_FAILURE, z);
            return this;
        }

        public ApkUpdateExtrasBuilder putErrorCode(int i) {
            putInt("error_code", i);
            return this;
        }

        public ApkUpdateExtrasBuilder putStackTrace(String str) {
            putString("stack_trace", str);
            return this;
        }

        public ApkUpdateExtrasBuilder putCanceledBy(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_CANCELED_BY, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putRetryOf(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_IS_RETRY_OF, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putRetriedUpdate(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_RETRIED_AS_ID, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putRecoveryTries(int i) {
            putLong(ApkUpdateInfoContract.EXTRA_RECOVERY_TRIES, (long) i);
            return this;
        }

        public ApkUpdateExtrasBuilder putUUID(String str) {
            putString("uuid", str);
            return this;
        }

        public ApkUpdateExtrasBuilder putFutureId(long j) {
            putLong(ApkUpdateInfoContract.EXTRA_FUTURE_ID, j);
            return this;
        }

        public ApkUpdateExtrasBuilder putFuturePid(int i) {
            putInt(ApkUpdateInfoContract.EXTRA_FUTURE_PID, i);
            return this;
        }

        public void save() {
            throw new IllegalStateException("Cannot save state.");
        }
    }

    public class UpdateExtrasEditor extends ApkUpdateExtrasBuilder {
        private final Set<String> mKeysToRemove = new HashSet();

        public UpdateExtrasEditor() {
        }

        @Override // com.oculus.appmanager.info.ApkUpdateInfo.ApkUpdateExtrasBuilder
        public ApkUpdateExtrasBuilder putState(ApkUpdateInfoContract.UpdateState updateState, long j) {
            if (ApkUpdateInfo.this.getState().isFinal()) {
                String format = String.format(Locale.US, "Unable to set state to %s, state is already final: %s", updateState.name(), ApkUpdateInfo.this);
                ApkUpdateInfo.this.mErrorReporter.softError(ErrorCategories.INSTALL_ILLEGAL_PUT_STATE, format, new IllegalArgumentException(format));
                if (ApkUpdateInfo.this.mDebugMode.isEnabled()) {
                    throw new IllegalArgumentException(format);
                }
            }
            return ApkUpdateInfo.this.getState() != updateState ? super.putState(updateState, j) : this;
        }

        @Override // com.oculus.extras.ExtrasBuilder
        public ApkUpdateExtrasBuilder remove(String str) {
            this.mKeysToRemove.add(str);
            return this;
        }

        @Override // com.oculus.appmanager.info.ApkUpdateInfo.ApkUpdateExtrasBuilder
        public void save() {
            HashSet<String> hashSet = new HashSet();
            HashMap hashMap = new HashMap();
            synchronized (ApkUpdateInfo.this) {
                hashSet.addAll(getData().keySet());
                hashSet.addAll(this.mKeysToRemove);
                HashMap hashMap2 = new HashMap(ApkUpdateInfo.this.getExtras().getData());
                Map<String, String> data = ApkUpdateInfo.this.mApkUpdateExtrasManager.getExtras(ApkUpdateInfo.this.id).getData();
                for (String str : hashSet) {
                    if (data.containsKey(str)) {
                        hashMap.put(str, data.get(str));
                    }
                }
                hashMap2.putAll(getData());
                for (String str2 : this.mKeysToRemove) {
                    hashMap2.remove(str2);
                }
                ApkUpdateInfo.this.setExtras(new ApkUpdateExtras(hashMap2));
                if (!ApkUpdateInfo.this.mDeleted.get() && ApkUpdateInfo.this.id != -1) {
                    ApkUpdateInfo.this.mApkUpdateExtrasManager.setExtras(ApkUpdateInfo.this.id, ApkUpdateInfo.this.getExtras());
                }
            }
            ApkUpdateInfo.this.mApkUpdateInfoDispatcher.notifyChanged(ApkUpdateInfo.this, hashSet, new ApkUpdateExtras(hashMap));
        }
    }
}
