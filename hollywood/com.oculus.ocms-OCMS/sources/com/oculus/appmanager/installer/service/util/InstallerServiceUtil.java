package com.oculus.appmanager.installer.service.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.RequiresPermission;
import com.facebook.common.android.AndroidModule;
import com.facebook.debug.log.BLog;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.SettableFuture;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.installer.common.CommonModule;
import com.oculus.appmanager.installer.common.InstallerFileUtils;
import com.oculus.appmanager.installer.contract.ContractModule;
import com.oculus.appmanager.installer.contract.InstallerServiceContract;
import com.oculus.appmanager.installer.service.util.UtilModule;
import com.oculus.appmanager.model.UpdateConfig;
import com.oculus.common.packagescache.PackageManagerUtils;
import com.oculus.common.packagescache.PackagesCacheModule;
import com.oculus.extras.Extras;
import com.oculus.horizon.R;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.library.model.App;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import com.oculus.util.thread.ThreadModule;
import com.oculus.util.thread.ThreadUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID"})
public class InstallerServiceUtil {
    private static final String FB_INSTALLER_PACKAGE = "com.facebook.system";
    private static final String TAG = "InstallerServiceUtil";
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UtilModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final InstallerServiceUtil _UL__ULSEP_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (InstallerServiceUtil) UL.factorymap.get(UtilModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final InstallerServiceUtil _UL__ULSEP_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new InstallerServiceUtil(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UtilModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULSEP_BINDING_ID, injectorLike);
    }

    /* access modifiers changed from: private */
    public static class InstalledPackageDetail {
        long installedVersion;
        boolean isUpdate;

        private InstalledPackageDetail() {
        }
    }

    @Inject
    public InstallerServiceUtil(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(7, injectorLike);
    }

    private boolean checkAssetRequestDuplicate(final String str, final UpdateConfig updateConfig) {
        return !((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).findUpdates(new Predicate<ApkUpdateInfo>() {
            /* class com.oculus.appmanager.installer.service.util.InstallerServiceUtil.AnonymousClass1 */

            public boolean apply(@Nullable ApkUpdateInfo apkUpdateInfo) {
                return apkUpdateInfo != null && apkUpdateInfo.identifier.equals(str) && apkUpdateInfo.updateType.isAsset() && TextUtils.equals(updateConfig.oculusStoreId, apkUpdateInfo.storeItemId) && !apkUpdateInfo.getState().isFinal();
            }
        }).isEmpty();
    }

    private boolean checkAppInstallRequestDuplicate(final String str, @Nullable final String str2, final long j, final long j2) {
        return !((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).findUpdates(new Predicate<ApkUpdateInfo>() {
            /* class com.oculus.appmanager.installer.service.util.InstallerServiceUtil.AnonymousClass2 */

            public boolean apply(@Nullable ApkUpdateInfo apkUpdateInfo) {
                return apkUpdateInfo != null && apkUpdateInfo.identifier.equals(str) && apkUpdateInfo.updateType.isApk() && TextUtils.equals(str2, apkUpdateInfo.storeItemId) && j == apkUpdateInfo.baseVersion && j2 == apkUpdateInfo.targetVersion && !apkUpdateInfo.getState().isFinal();
            }
        }).isEmpty();
    }

    public SettableFuture<InstallerResult> startServiceForStartAssetDownloadAction(UpdateConfig updateConfig, String str, String str2, @Nullable String str3, RequestOrigin requestOrigin) {
        ((ThreadUtils) FbInjector.lazyInstance(4, ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).assertIsNonUiThread();
        String string = new ApkUpdateInfo.ApkUpdateExtras(new Extras(updateConfig.extras)).getString(ApkUpdateInfoContract.EXTRA_ASSET_REQUIRED_FILENAME, null);
        if (TextUtils.isEmpty(string)) {
            BLog.e(TAG, "missing required filename extra. Cannot start service for asset download");
            return createFutureForError(str, InstallerResultError.INVALID_INSTALL_REQUEST);
        } else if (checkAssetRequestDuplicate(str, updateConfig)) {
            BLog.w(TAG, "same application(asset) has a non-final status download request, abort!");
            return createFutureForError(str, InstallerResultError.DUPLICATE_REQUEST);
        } else {
            boolean z = ((PackageManagerUtils) FbInjector.lazyInstance(1, PackagesCacheModule.UL_id._UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isPackageInstalled(str) && ((InstallerFileUtils) FbInjector.lazyInstance(5, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isAssetInstalled(str, string);
            long installedVersionCode = ((PackageManagerUtils) FbInjector.lazyInstance(1, PackagesCacheModule.UL_id._UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getInstalledVersionCode(str, LibraryDBContract.VERSION_NOT_INSTALLED);
            return ((InstallerServiceContract) FbInjector.lazyInstance(2, ContractModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).downloadAndInstall(((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).addUpdate(str, ApkUpdateInfoContract.UpdateType.STORE_FULL_ASSET, installedVersionCode, installedVersionCode, z, updateConfig.downloadSize, updateConfig.downloadUrl, requestOrigin, ImmutableList.of(), str2, updateConfig.oculusStoreId, updateConfig.downloadChecksum, updateConfig.downloadChecksumType, updateConfig.downloadName, str3, null, null, new ApkUpdateInfo.ApkUpdateExtrasBuilder().putPackageName(str).putAssetRequiredFilename(string).build(), null));
        }
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public SettableFuture<InstallerResult> startServiceForInstallAction(String str, UpdateConfig updateConfig, @Nullable UpdateConfig updateConfig2, @Nullable List<UpdateConfig> list, @Nullable String str2, @Nullable String str3, long j, @Nullable String str4, RequestOrigin requestOrigin) {
        UpdateConfig updateConfig3;
        InstalledPackageDetail installedPackageDetail;
        String str5;
        ((ThreadUtils) FbInjector.lazyInstance(4, ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).assertIsNonUiThread();
        InstalledPackageDetail installedPackageInfo = getInstalledPackageInfo(str2);
        if (checkAppInstallRequestDuplicate(str, str3, installedPackageInfo.installedVersion, j)) {
            BLog.w(TAG, "same application has a non-final status download request, abort!");
            return createFutureForError(str, InstallerResultError.DUPLICATE_REQUEST);
        }
        ArrayList arrayList = new ArrayList();
        if (updateConfig2 != null) {
            installedPackageDetail = installedPackageInfo;
            str5 = str;
            updateConfig3 = updateConfig;
            arrayList.add(Long.valueOf(((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).addUpdate(str, updateConfig2.updateType, installedPackageInfo.installedVersion, j, installedPackageInfo.isUpdate, updateConfig2.downloadSize, updateConfig2.downloadUrl, requestOrigin, ImmutableList.of(), updateConfig2.accessToken, str3, updateConfig2.downloadChecksum, updateConfig2.downloadChecksumType, updateConfig2.downloadName, str4, null, null, new Extras(updateConfig2.extras), ImmutableMap.copyOf(updateConfig2.downloadHeaders)).id));
        } else {
            str5 = str;
            updateConfig3 = updateConfig;
            installedPackageDetail = installedPackageInfo;
        }
        if (list != null) {
            for (UpdateConfig updateConfig4 : list) {
                if (!updateConfig4.updateType.isFromStore() || !TextUtils.isEmpty(updateConfig4.oculusStoreId)) {
                    arrayList.add(Long.valueOf(((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).addUpdate(str, updateConfig4.updateType, installedPackageDetail.installedVersion, j, installedPackageDetail.isUpdate, updateConfig4.downloadSize, updateConfig4.downloadUrl, requestOrigin, ImmutableList.of(), updateConfig4.accessToken, updateConfig4.oculusStoreId, updateConfig4.downloadChecksum, updateConfig4.downloadChecksumType, updateConfig4.downloadName, str4, null, null, new Extras(updateConfig4.extras), ImmutableMap.copyOf(updateConfig4.downloadHeaders)).id));
                } else {
                    String string = new ApkUpdateInfo.ApkUpdateExtras(new Extras(updateConfig4.extras)).getString(ApkUpdateInfoContract.EXTRA_ASSET_REQUIRED_FILENAME, null);
                    String str6 = TAG;
                    BLog.e(str6, "Missing id for asset download. Cannot update asset for " + updateConfig4.downloadName + " with filename " + string);
                    return createFutureForError(str5, InstallerResultError.INVALID_INSTALL_REQUEST);
                }
            }
        }
        return ((InstallerServiceContract) FbInjector.lazyInstance(2, ContractModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).downloadAndInstall(((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).addUpdate(str, updateConfig3.updateType, installedPackageDetail.installedVersion, j, installedPackageDetail.isUpdate, updateConfig3.downloadSize, updateConfig3.downloadUrl, requestOrigin, ImmutableList.copyOf((Collection) arrayList), updateConfig3.accessToken, str3, updateConfig3.downloadChecksum, updateConfig3.downloadChecksumType, updateConfig3.downloadName, str4, updateConfig3.signature, updateConfig3.externalSignatures, new Extras(updateConfig3.extras), ImmutableMap.copyOf(updateConfig3.downloadHeaders)));
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public SettableFuture<InstallerResult> startServiceForUninstall(String str, @Nullable String str2, RequestOrigin requestOrigin) {
        return startServiceForUninstallAction(str, null, null, str2, requestOrigin);
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public SettableFuture<InstallerResult> startServiceForEntitlementUninstall(App app, @Nullable String str, RequestOrigin requestOrigin) {
        return startServiceForUninstallAction(app.packageName, app.id, app.displayName, str, requestOrigin);
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    private SettableFuture<InstallerResult> startServiceForUninstallAction(String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, RequestOrigin requestOrigin) {
        ((ThreadUtils) FbInjector.lazyInstance(4, ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).assertIsNonUiThread();
        if (!((PackageManagerUtils) FbInjector.lazyInstance(1, PackagesCacheModule.UL_id._UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isPackageInstalled(str)) {
            return createFutureForError(str, InstallerResultError.NOT_INSTALLED);
        }
        ApkUpdateInfo.ApkUpdateExtrasBuilder apkUpdateExtrasBuilder = new ApkUpdateInfo.ApkUpdateExtrasBuilder();
        apkUpdateExtrasBuilder.putPackageName(str);
        ApkUpdateInfo addUpdate = ((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).addUpdate(str, ApkUpdateInfoContract.UpdateType.UNINSTALL, LibraryDBContract.VERSION_NOT_INSTALLED, LibraryDBContract.VERSION_NOT_INSTALLED, false, -1, null, requestOrigin, null, null, str2, null, null, str3, str4, null, null, apkUpdateExtrasBuilder.build(), null);
        if (addUpdate != null) {
            return ((InstallerServiceContract) FbInjector.lazyInstance(2, ContractModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).uninstall(addUpdate);
        }
        BLog.e(TAG, "Error creating update info for uninstall");
        return createFutureForError(str, InstallerResultError.UNKNOWN_ERROR);
    }

    private SettableFuture<InstallerResult> createFutureForError(String str, InstallerResultError installerResultError) {
        SettableFuture<InstallerResult> create = SettableFuture.create();
        create.set(InstallerResult.createForError(str, ((Clock) FbInjector.lazyInstance(6, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now(), installerResultError));
        return create;
    }

    public SettableFuture<InstallerResult> cancelAssetDownload(final String str, final long j) {
        return startServiceForCancelPredicate(str, new Predicate<ApkUpdateInfo>() {
            /* class com.oculus.appmanager.installer.service.util.InstallerServiceUtil.AnonymousClass3 */

            public boolean apply(@Nullable ApkUpdateInfo apkUpdateInfo) {
                return apkUpdateInfo != null && !((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, InstallerServiceUtil.this._UL_mInjectionContext)).hasParentUpdate(apkUpdateInfo) && apkUpdateInfo.updateType.isAsset() && Long.toString(j).equals(apkUpdateInfo.storeItemId) && apkUpdateInfo.identifier.equals(str) && apkUpdateInfo.getState().isCancelable() && !apkUpdateInfo.isDeleted();
            }
        });
    }

    public SettableFuture<InstallerResult> cancelAssetDownload(final String str, final String str2) {
        return startServiceForCancelPredicate(str, new Predicate<ApkUpdateInfo>() {
            /* class com.oculus.appmanager.installer.service.util.InstallerServiceUtil.AnonymousClass4 */

            public boolean apply(@Nullable ApkUpdateInfo apkUpdateInfo) {
                return apkUpdateInfo != null && !((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, InstallerServiceUtil.this._UL_mInjectionContext)).hasParentUpdate(apkUpdateInfo) && apkUpdateInfo.updateType.isAsset() && str2.equals(apkUpdateInfo.getExtras().getAssetRequiredFilename()) && apkUpdateInfo.identifier.equals(str) && apkUpdateInfo.getState().isCancelable() && !apkUpdateInfo.isDeleted();
            }
        });
    }

    public SettableFuture<InstallerResult> cancelAllInstallsForIdentifier(final String str) {
        return startServiceForCancelPredicate(str, new Predicate<ApkUpdateInfo>() {
            /* class com.oculus.appmanager.installer.service.util.InstallerServiceUtil.AnonymousClass5 */

            public boolean apply(@Nullable ApkUpdateInfo apkUpdateInfo) {
                return apkUpdateInfo != null && !((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, InstallerServiceUtil.this._UL_mInjectionContext)).hasParentUpdate(apkUpdateInfo) && apkUpdateInfo.identifier.equals(str) && apkUpdateInfo.getState().isCancelable() && !apkUpdateInfo.isDeleted();
            }
        });
    }

    public SettableFuture<InstallerResult> startServiceForCancelPredicate(String str, Predicate<ApkUpdateInfo> predicate) {
        ((ThreadUtils) FbInjector.lazyInstance(4, ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).assertIsNonUiThread();
        List<ApkUpdateInfo> findUpdates = ((InfoUtils) FbInjector.lazyInstance(3, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).findUpdates(predicate);
        BLog.i(TAG, "Found %d updates to cancel", Integer.valueOf(findUpdates.size()));
        if (findUpdates.isEmpty()) {
            BLog.e(TAG, "No running updateInfo downloads found");
            SettableFuture<InstallerResult> create = SettableFuture.create();
            create.set(InstallerResult.createForError(str, ((Clock) FbInjector.lazyInstance(6, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now(), InstallerResultError.INVALID_REQUEST));
            return create;
        }
        if (findUpdates.size() != 1) {
            BLog.e(TAG, "Found multiple updateInfo downloads to cancel: %d", Integer.valueOf(findUpdates.size()));
        }
        SettableFuture<InstallerResult> settableFuture = null;
        for (ApkUpdateInfo apkUpdateInfo : findUpdates) {
            settableFuture = ((InstallerServiceContract) FbInjector.lazyInstance(2, ContractModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancel(apkUpdateInfo);
        }
        return settableFuture;
    }

    public String getObbDisplayTitle(String str) {
        return ((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getString(R.string.obb_display_title, str);
    }

    private static void setInstallerServiceComponent(Intent intent) {
        intent.setComponent(new ComponentName(InstallerServiceContract.INSTALLER_PACKAGE, InstallerServiceContract.INSTALLER_CLASS));
    }

    private void startService(Intent intent) {
        setInstallerServiceComponent(intent);
        if (((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID, this._UL_mInjectionContext)).startService(intent) == null) {
            throw new IllegalArgumentException("Failed to start service for intent: " + intent);
        }
    }

    public boolean isInstallerPackagePresent() {
        try {
            return ((PackageManager) FbInjector.localInstance(AndroidModule.UL_id._UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getPackageInfo("com.facebook.system", 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public boolean isInstallerPackageEnabled() {
        try {
            PackageInfo packageInfo = ((PackageManager) FbInjector.localInstance(AndroidModule.UL_id._UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getPackageInfo("com.facebook.system", 0);
            if (packageInfo == null || packageInfo.applicationInfo == null || !packageInfo.applicationInfo.enabled) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static Intent getApplicationDetailsSettingsIntent() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addFlags(268435456);
        intent.setData(Uri.parse("package:com.facebook.system"));
        return intent;
    }

    private InstalledPackageDetail getInstalledPackageInfo(String str) {
        InstalledPackageDetail installedPackageDetail = new InstalledPackageDetail();
        installedPackageDetail.isUpdate = ((PackageManagerUtils) FbInjector.lazyInstance(1, PackagesCacheModule.UL_id._UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isPackageInstalled(str);
        installedPackageDetail.installedVersion = ((PackageManagerUtils) FbInjector.lazyInstance(1, PackagesCacheModule.UL_id._UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getInstalledVersionCode(str, LibraryDBContract.VERSION_NOT_INSTALLED);
        return installedPackageDetail;
    }
}
