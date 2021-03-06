package com.oculus.appmanager.installer.service;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.util.StringLocaleUtil;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.UL;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.oculus.appmanager.constants.OculusAppManagerErrorCodes;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.installer.analytics.InstallerAnalytics;
import com.oculus.appmanager.installer.common.InstallerFileUtils;
import com.oculus.appmanager.installer.contract.errors.ErrorCategories;
import com.oculus.appmanager.installer.events.DownloadResponse;
import com.oculus.appmanager.installer.service.InstallerServiceModule;
import com.oculus.downloader.DownloaderModule;
import com.oculus.downloader.OculusDownloader;
import com.oculus.downloader.contract.DownloaderContract;
import com.oculus.downloader.extras.contract.DownloadExtrasKeys;
import com.oculus.downloader.model.DownloadConfig;
import com.oculus.downloader.model.DownloadInfo;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.extras.Extras;
import com.oculus.http.core.common.Authorization;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_downloader_OculusDownloader_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerEventEmitter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCleanUpHelper_ULSEP_BINDING_ID"})
@ApplicationScoped
public class InstallerServiceDownloadHelper {
    private static final String HTTPS_CHANNEL = "https";
    private static final String[] PUBLIC_CDN_HOST_NAMES = {"o.oculuscdn.com", ".fbcdn.net"};
    private static final String SECURE_CDN_HOST_NAME = "securecdn.oculus.com";
    private static final String SOFT_ERROR_CANCEL_DOWNLOADING_NO_ID = "cancel_no_download_id";
    private static final String TAG = "InstallerServiceDownloadHelper";
    private static volatile InstallerServiceDownloadHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;
    @UnsafeContextInjection
    @Inject
    @Eager
    private final Context mContext;
    @Inject
    @Eager
    private final InfoUtils mInfoUtils;
    @Inject
    @Eager
    private final InstallerAnalytics mInstallerAnalytics;
    @Inject
    @Eager
    private final InstallerCleanUpHelper mInstallerCleanUpHelper;
    @Inject
    @Eager
    private final InstallerEventEmitter mInstallerEventEmitter;
    @Inject
    @Eager
    private final InstallerFileUtils mInstallerFileUtils;
    @Inject
    @Eager
    private final OculusDownloader mOculusFileDownloader;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final InstallerServiceDownloadHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (InstallerServiceDownloadHelper) UL.factorymap.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final InstallerServiceDownloadHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_INSTANCE == null) {
            synchronized (InstallerServiceDownloadHelper.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_INSTANCE = new InstallerServiceDownloadHelper(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public InstallerServiceDownloadHelper(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(2, injectorLike);
        this.mContext = BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike);
        this.mInfoUtils = InfoUtils._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_ACCESS_METHOD(injectorLike);
        this.mInstallerAnalytics = InstallerAnalytics._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_ACCESS_METHOD(injectorLike);
        this.mInstallerFileUtils = InstallerFileUtils._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_ACCESS_METHOD(injectorLike);
        this.mOculusFileDownloader = DownloaderModule._UL__ULSEP_com_oculus_downloader_OculusDownloader_ULSEP_ACCESS_METHOD(injectorLike);
        this.mInstallerEventEmitter = InstallerEventEmitter._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerEventEmitter_ULSEP_ACCESS_METHOD(injectorLike);
        this.mInstallerCleanUpHelper = InstallerCleanUpHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCleanUpHelper_ULSEP_ACCESS_METHOD(injectorLike);
    }

    private static ImmutableMap<String, String> buildDownloadExtras(ApkUpdateInfo apkUpdateInfo, int i) {
        return new ImmutableMap.Builder().put("update_id", Long.toString(apkUpdateInfo.id)).put(DownloadExtrasKeys.KEY_NETWORKS, String.valueOf(i)).build();
    }

    @Nullable
    private static Map<String, String> maybeAddAuthHeader(@Nullable Map<String, String> map, String str, String str2) {
        Uri parse = Uri.parse(str);
        if ("https".equals(parse.getScheme())) {
            String host = parse.getHost();
            for (String str3 : PUBLIC_CDN_HOST_NAMES) {
                if (host.endsWith(str3)) {
                    return map;
                }
            }
            if (SECURE_CDN_HOST_NAME.equals(parse.getHost())) {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                if (map == null) {
                    map = ImmutableMap.of();
                }
                return builder.putAll(map).put("Authorization", Authorization.generate(str2)).build();
            }
            BLog.e(TAG, "Authorization header not added because host name is not allowed, uri: %s", str);
            throw new IllegalArgumentException("Trying to download on from non-oculus hostname. Uri " + str);
        }
        BLog.e(TAG, "Authorization header not added because request is insecure, uri: %s", str);
        throw new IllegalArgumentException("Trying to download on non-https with authorization. Uri " + str);
    }

    /* access modifiers changed from: package-private */
    public void download(ApkUpdateInfo apkUpdateInfo) {
        int i = apkUpdateInfo.requestOrigin == RequestOrigin.AUTOUPDATE ? 2 : 3;
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.mContext.getPackageManager().getApplicationInfo(DownloaderContract.PACKAGE_NAME, 0);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (applicationInfo == null || !applicationInfo.enabled) {
            this.mInstallerAnalytics.reportInstallFunnelDownloadManagerDisabled(apkUpdateInfo);
            onDownloadPreparationFailure(apkUpdateInfo, "Download Manager disabled", DownloadResponse.ERROR_DOWNLOAD_MANAGER_NOT_FOUND, InstallerResultError.DOWNLOADER_DISABLED);
            return;
        }
        DownloadConfig createDownloadConfig = createDownloadConfig(apkUpdateInfo, i);
        if (createDownloadConfig == null) {
            this.mInstallerAnalytics.reportInstallFunnelDownloadConfigFailed(apkUpdateInfo);
            return;
        }
        this.mInstallerFileUtils.removeFileWithReason(createDownloadConfig.fileUri, "clearing reserved download path");
        long enqueue = this.mOculusFileDownloader.enqueue(createDownloadConfig, new Extras(buildDownloadExtras(apkUpdateInfo, i)));
        String absoluteDownloadPath = DownloaderContract.getAbsoluteDownloadPath(createDownloadConfig.fileUri);
        maybeNotifyDownloading(apkUpdateInfo);
        apkUpdateInfo.edit().putState(ApkUpdateInfoContract.UpdateState.DOWNLOADING, ((Clock) FbInjector.lazyInstance(0, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now()).putDownloadId(enqueue).putDownloadingExternalFile(absoluteDownloadPath).save();
    }

    private void maybeNotifyDownloading(ApkUpdateInfo apkUpdateInfo) {
        ApkUpdateInfo rootUpdate = this.mInfoUtils.getRootUpdate(apkUpdateInfo);
        if (this.mInfoUtils.searchEntireDependencyTree(rootUpdate, new Predicate<ApkUpdateInfo>() {
            /* class com.oculus.appmanager.installer.service.InstallerServiceDownloadHelper.AnonymousClass1 */

            public boolean apply(@Nullable ApkUpdateInfo apkUpdateInfo) {
                if (apkUpdateInfo == null) {
                    return false;
                }
                ApkUpdateInfoContract.UpdateState state = apkUpdateInfo.getState();
                if (state.isFinal() || (state.isTransient() && state != ApkUpdateInfoContract.UpdateState.QUEUED_DOWNLOAD)) {
                    return true;
                }
                return false;
            }
        }).isEmpty()) {
            this.mInstallerEventEmitter.notifyDownloading(rootUpdate);
        }
    }

    public boolean cancelAnyRunningDownloads(ApkUpdateInfo apkUpdateInfo) {
        if (apkUpdateInfo.getState() == ApkUpdateInfoContract.UpdateState.DOWNLOADING) {
            if (apkUpdateInfo.getExtras().hasKey("download_id")) {
                return this.mOculusFileDownloader.remove(apkUpdateInfo.getExtras().getDownloadId());
            }
            BLog.e(TAG, "Attempting to cancel downloading update, but found no download id");
            ((IErrorReporter) FbInjector.lazyInstance(1, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(SOFT_ERROR_CANCEL_DOWNLOADING_NO_ID, "Attempting to cancel downloading update, but found no download id");
        }
        return false;
    }

    @Nullable
    @VisibleForTesting
    public DownloadConfig createDownloadConfig(ApkUpdateInfo apkUpdateInfo, int i) {
        if (TextUtils.isEmpty(apkUpdateInfo.downloadUrl)) {
            this.mInfoUtils.failHard(apkUpdateInfo, ErrorCategories.DOWNLOAD_URI_MISSING, "null download url", InstallerResultError.INVALID_INSTALL_REQUEST, null);
            return null;
        }
        String str = apkUpdateInfo.downloadUrl;
        Map<String, String> map = apkUpdateInfo.requestHeaders;
        if (apkUpdateInfo.updateType.isFromStore() && !TextUtils.isEmpty(apkUpdateInfo.accessToken)) {
            if (TextUtils.isEmpty(str)) {
                this.mInfoUtils.failSoft(apkUpdateInfo, ErrorCategories.DOWNLOAD_URI_MISSING, "update has empty or missing download url.", InstallerResultError.NOT_LOGGED_IN, null);
                return null;
            }
            map = maybeAddAuthHeader(map, str, apkUpdateInfo.accessToken);
        }
        String downloadingFilename = this.mInstallerFileUtils.getDownloadingFilename(apkUpdateInfo);
        if (downloadingFilename != null) {
            return new DownloadConfig(str, downloadingFilename, null, i, 0, apkUpdateInfo.displayTitle, apkUpdateInfo.identifier, map);
        }
        this.mInfoUtils.failSoft(apkUpdateInfo, ErrorCategories.DOWNLOAD_LOCAL_FILENAME_FAILED, "Cannot determine local downloading file Path for update", InstallerResultError.UNKNOWN_ERROR, null);
        return null;
    }

    private void onDownloadPreparationFailure(ApkUpdateInfo apkUpdateInfo, String str, int i, InstallerResultError installerResultError) {
        BLog.e(TAG, "Failed to prepare download for update %s. Message: %s", apkUpdateInfo, str);
        String format = String.format(Locale.US, "Download prep failure. Code: %d, original message: %s", Integer.valueOf(i), str);
        apkUpdateInfo.edit().putDownloadReason(i).putErrorCode(i).save();
        this.mInfoUtils.failQuiet(apkUpdateInfo, ErrorCategories.DOWNLOAD_PREP_FAILED, format, installerResultError, null);
    }

    /* access modifiers changed from: package-private */
    public void onDownloadComplete(long j) {
        DownloadInfo query = this.mOculusFileDownloader.query(j);
        if (query != null) {
            onDownloadComplete(query);
            return;
        }
        BLog.w(TAG, "Received download completion for id %s but cannot find associated downloadInfo", Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public void onDownloadComplete(final DownloadInfo downloadInfo) {
        ApkUpdateInfo fromExtras = this.mInfoUtils.getFromExtras(downloadInfo.extras, "update_id");
        if (fromExtras != null) {
            onDownloadComplete(downloadInfo, fromExtras);
            return;
        }
        List<ApkUpdateInfo> findUpdates = this.mInfoUtils.findUpdates(new Predicate<ApkUpdateInfo>() {
            /* class com.oculus.appmanager.installer.service.InstallerServiceDownloadHelper.AnonymousClass2 */

            public boolean apply(@Nullable ApkUpdateInfo apkUpdateInfo) {
                if (apkUpdateInfo == null) {
                    return false;
                }
                boolean z = downloadInfo.description.isPresent() && !TextUtils.isEmpty(downloadInfo.description.get()) && downloadInfo.description.get().equals(apkUpdateInfo.identifier);
                boolean z2 = !downloadInfo.description.isPresent() || TextUtils.isEmpty(downloadInfo.description.get());
                if ((z || z2) && apkUpdateInfo.getExtras().getDownloadId() == downloadInfo.id && apkUpdateInfo.getState().equals(ApkUpdateInfoContract.UpdateState.DOWNLOADING)) {
                    return true;
                }
                return false;
            }
        });
        if (findUpdates.size() == 1) {
            onDownloadComplete(downloadInfo, findUpdates.get(0));
        }
    }

    @VisibleForTesting
    public void onDownloadComplete(DownloadInfo downloadInfo, ApkUpdateInfo apkUpdateInfo) {
        long j;
        long j2;
        InstallerResultError installerResultError;
        String orNull = downloadInfo.localUri.orNull();
        if (apkUpdateInfo.getState().isFinal()) {
            apkUpdateInfo.edit().putDownloadedExternalFile(orNull).save();
            this.mInstallerCleanUpHelper.cleanUpStorage(apkUpdateInfo);
            return;
        }
        long j3 = -1;
        long j4 = downloadInfo.extras.getLong(DownloadExtrasKeys.KEY_DOWNLOAD_SCHEDULED_TIME_MS, -1);
        long j5 = downloadInfo.extras.getLong(DownloadExtrasKeys.KEY_DOWNLOAD_START_TIME_MS, -1);
        long elapsedRealtime = ((Clock) FbInjector.lazyInstance(0, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).elapsedRealtime();
        if (j4 == -1 || j5 == -1) {
            j = elapsedRealtime;
            j2 = -1;
        } else {
            j = elapsedRealtime;
            j2 = TimeUnit.MILLISECONDS.toSeconds(j5 - j4);
        }
        if (j5 != -1) {
            j3 = TimeUnit.MILLISECONDS.toSeconds(j - j5);
        }
        long j6 = downloadInfo.totalSizeBytes;
        if (downloadInfo.status != 16 || downloadInfo.reason != 1056) {
            if (downloadInfo.status != 8) {
                if (downloadInfo.reason == 404) {
                    installerResultError = InstallerResultError.AUTHORIZATION_ERROR;
                } else {
                    installerResultError = InstallerResultError.NETWORK_ERROR;
                }
                BLog.e(TAG, "download was not successful");
                onDownloadFailed(apkUpdateInfo, downloadInfo.status, downloadInfo.reason, installerResultError, null);
            } else if (TextUtils.isEmpty(orNull)) {
                BLog.e(TAG, "downloaded file has a null or empty path");
                this.mInstallerAnalytics.reportInstallFunnelDownloadedFileNotFound(apkUpdateInfo);
                onDownloadFailed(apkUpdateInfo, downloadInfo.status, downloadInfo.reason, InstallerResultError.IO_ERROR, null);
            } else {
                try {
                    apkUpdateInfo.edit().putDownloadReason(downloadInfo.reason).putDownloadStatus(downloadInfo.status).putDownloadedExternalFile(this.mInstallerFileUtils.moveToDownloadedLocation(apkUpdateInfo)).putDownloadPendingDurationSec(j2).putDownloadRunningDurationSec(j3).putDownloadScheduledTime(j4).putDownloadStartTime(j5).putDownloadedSize(j6).save();
                    BLog.d(TAG, "Download Succeeded for identifier: %s. Download duration, pending: %ss, running: %ss", apkUpdateInfo.identifier, Long.valueOf(apkUpdateInfo.getExtras().getDownloadPendingDurationSec()), Long.valueOf(apkUpdateInfo.getExtras().getDownloadRunningDurationSec()));
                    apkUpdateInfo.edit().putState(ApkUpdateInfoContract.UpdateState.DOWNLOADED, ((Clock) FbInjector.lazyInstance(0, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now()).save();
                } catch (IOException e) {
                    onDownloadFailed(apkUpdateInfo, downloadInfo.status, OculusAppManagerErrorCodes.ERROR_IO_EXCEPTION_DURING_RENAME_DOWNLOADING, InstallerResultError.IO_ERROR, e);
                }
            }
        }
    }

    private void onDownloadFailed(ApkUpdateInfo apkUpdateInfo, int i, int i2, InstallerResultError installerResultError, Throwable th) {
        String str;
        if (th == null) {
            str = StringLocaleUtil.formatStrLocaleSafe("Download failed. reason: %d", Integer.valueOf(i2));
        } else {
            str = th.getClass().getSimpleName() + ": " + th.getMessage();
        }
        apkUpdateInfo.edit().putDownloadStatus(i).putDownloadReason(i2).save();
        this.mInfoUtils.failQuiet(apkUpdateInfo, ErrorCategories.DOWNLOAD_FAILED, str, installerResultError, th);
    }
}
