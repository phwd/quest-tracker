package com.oculus.appmanager.installer.service;

import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.base.Preconditions;
import com.oculus.appmanager.assets.AssetStorage;
import com.oculus.appmanager.assets.AssetsModule;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.installer.analytics.AnalyticsModule;
import com.oculus.appmanager.installer.analytics.InstallerAnalytics;
import com.oculus.appmanager.installer.common.CommonModule;
import com.oculus.appmanager.installer.common.InstallerFileUtils;
import com.oculus.appmanager.installer.contract.errors.ErrorCategories;
import com.oculus.appmanager.installer.service.InstallerServiceModule;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.library.model.AssetInfo;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_broadcast_AssetBroadcastDispatch_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerRetryHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_assets_AssetStorage_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCleanUpHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerEventEmitter_ULSEP_BINDING_ID"})
@ApplicationScoped
public class InstallerServiceInstallHelper {
    private static final int INSTALL_ERROR_CODE_BAD_CALL = 99;
    private static final String NO_CERTIFICATES_ERROR = "INSTALL_PARSE_FAILED_NO_CERTIFICATES";
    private static final String TAG = "InstallerServiceInstallHelper";
    private static volatile InstallerServiceInstallHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final InstallerServiceInstallHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (InstallerServiceInstallHelper) UL.factorymap.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final InstallerServiceInstallHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_INSTANCE == null) {
            synchronized (InstallerServiceInstallHelper.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_INSTANCE = new InstallerServiceInstallHelper(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public InstallerServiceInstallHelper(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(12, injectorLike);
    }

    public void handleInstallCompletedIntent(ApkUpdateInfo apkUpdateInfo, int i, @Nullable String str) {
        if (i == 0) {
            onApkInstallSuccess(apkUpdateInfo);
        } else {
            onApkInstallFailed(apkUpdateInfo, null, str, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x011a, code lost:
        if (((com.oculus.appmanager.installer.common.InstallerFileUtils) com.facebook.inject.FbInjector.lazyInstance(1, com.oculus.appmanager.installer.common.CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, r16._UL_mInjectionContext)).isAssetInstalled(r11, r2.name) != false) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x011c, code lost:
        com.facebook.debug.log.BLog.d(com.oculus.appmanager.installer.service.InstallerServiceInstallHelper.TAG, "No installed asset found for %s. No backup will be made", r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0124, code lost:
        r2 = ((com.oculus.appmanager.installer.common.InstallerFileUtils) com.facebook.inject.FbInjector.lazyInstance(1, com.oculus.appmanager.installer.common.CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, r16._UL_mInjectionContext)).getAssetBackupPath(r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0136, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0137, code lost:
        ((com.oculus.appmanager.installer.common.InstallerFileUtils) com.facebook.inject.FbInjector.lazyInstance(1, com.oculus.appmanager.installer.common.CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, r16._UL_mInjectionContext)).removeFileWithReason(r2, "Removing backup file after failed backup");
        ((com.oculus.appmanager.info.InfoUtils) com.facebook.inject.FbInjector.lazyInstance(2, com.oculus.appmanager.info.InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, r16._UL_mInjectionContext)).failQuiet(r17, com.oculus.appmanager.installer.contract.errors.ErrorCategories.INSTALL_FILE_MOVE, "failed to backup previously installed asset", com.oculus.appmanager.info.model.InstallerResultError.IO_ERROR, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x015d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void installAsset(com.oculus.appmanager.info.ApkUpdateInfo r17) {
        /*
        // Method dump skipped, instructions count: 629
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.appmanager.installer.service.InstallerServiceInstallHelper.installAsset(com.oculus.appmanager.info.ApkUpdateInfo):void");
    }

    public void installOrUpdateApp(ApkUpdateInfo apkUpdateInfo) {
        InfoUtils.UpdateInfoCollection updateInfoCollection = ((InfoUtils) FbInjector.lazyInstance(2, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getUpdateInfoCollection(apkUpdateInfo);
        String packageName = apkUpdateInfo.getPackageName();
        Preconditions.checkArgument(!TextUtils.isEmpty(packageName));
        Preconditions.checkArgument(updateInfoCollection.obb == null || updateInfoCollection.obb.getState() == ApkUpdateInfoContract.UpdateState.VERIFIED, "Expected obb in verified state");
        for (ApkUpdateInfo apkUpdateInfo2 : updateInfoCollection.assets) {
            boolean z = apkUpdateInfo2.getState() == ApkUpdateInfoContract.UpdateState.VERIFIED;
            Preconditions.checkArgument(z, "Expected asset in verified state, found: " + apkUpdateInfo2.getState());
        }
        ArrayList arrayList = new ArrayList(updateInfoCollection.assets);
        if (updateInfoCollection.obb != null) {
            arrayList.add(updateInfoCollection.obb);
        }
        ArrayList<ApkUpdateInfo> arrayList2 = new ArrayList(arrayList);
        arrayList2.add(apkUpdateInfo);
        BLog.i(TAG, "Starting install for %s. obb: %s. asset count: %s", packageName, updateInfoCollection.obb != null ? "present" : "not present", Integer.valueOf(updateInfoCollection.assets.size()));
        for (ApkUpdateInfo apkUpdateInfo3 : arrayList2) {
            apkUpdateInfo3.edit().putState(ApkUpdateInfoContract.UpdateState.INSTALLING, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now()).putInstallationStartTimeMs(((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now()).save();
        }
        ((InstallerAnalytics) FbInjector.lazyInstance(3, AnalyticsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportInstallStarted(updateInfoCollection);
        ((InstallerAnalytics) FbInjector.lazyInstance(3, AnalyticsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportInstallFunnelInstallStarted(apkUpdateInfo);
        try {
            for (ApkUpdateInfo apkUpdateInfo4 : arrayList) {
                String finalPatchedExternalFilePath = apkUpdateInfo4.getExtras().getFinalPatchedExternalFilePath();
                String installationPreparationPath = ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getInstallationPreparationPath(apkUpdateInfo4);
                apkUpdateInfo4.edit().putPreparedInstallFilePath(installationPreparationPath).save();
                move(finalPatchedExternalFilePath, installationPreparationPath);
            }
            BLog.d(TAG, "asset and obb preparation has completed");
            try {
                doInstallVerifiedApk(apkUpdateInfo);
            } catch (Throwable th) {
                deletePreparedFiles(arrayList, "Removing prepared file for failed install");
                onApkInstallFailed(apkUpdateInfo, th, String.format(Locale.US, "Exception raised while performing install request: %s", th.getMessage()), 99);
            }
        } catch (IOException e) {
            deletePreparedFiles(arrayList, "Removing prior prepared file during preparation failure");
            ((InfoUtils) FbInjector.lazyInstance(2, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failQuiet(apkUpdateInfo, ErrorCategories.INSTALL_FILE_MOVE, "failed to move files for install preparation", InstallerResultError.IO_ERROR, e);
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void onApkInstallSuccess(ApkUpdateInfo apkUpdateInfo) {
        apkUpdateInfo.edit().putInstallationDurationMs(getDuration(apkUpdateInfo.getExtras().getInstallationStartTimeMs())).save();
        Set<String> persistentAssetNames = apkUpdateInfo.getExtras().getPersistentAssetNames();
        InfoUtils.UpdateInfoCollection updateInfoCollection = ((InfoUtils) FbInjector.lazyInstance(2, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getUpdateInfoCollection(apkUpdateInfo);
        String packageName = apkUpdateInfo.getPackageName();
        Preconditions.checkArgument(!TextUtils.isEmpty(packageName));
        Preconditions.checkArgument(updateInfoCollection.obb == null || updateInfoCollection.obb.getState() == ApkUpdateInfoContract.UpdateState.INSTALLING, "Expected obb in installing state");
        for (ApkUpdateInfo apkUpdateInfo2 : updateInfoCollection.assets) {
            boolean z = apkUpdateInfo2.getState() == ApkUpdateInfoContract.UpdateState.INSTALLING;
            Preconditions.checkArgument(z, "Expected asset in verified state, found: " + apkUpdateInfo2.getState());
        }
        ArrayList arrayList = new ArrayList(updateInfoCollection.assets);
        if (updateInfoCollection.obb != null) {
            arrayList.add(updateInfoCollection.obb);
        }
        ArrayList<ApkUpdateInfo> arrayList2 = new ArrayList(arrayList);
        arrayList2.add(apkUpdateInfo);
        HashMap hashMap = new HashMap();
        for (ApkUpdateInfo apkUpdateInfo3 : updateInfoCollection.assets) {
            hashMap.put(apkUpdateInfo3.getExtras().getAssetRequiredFilename(), apkUpdateInfo3);
        }
        List<AssetInfo> assetList = ((AssetStorage) FbInjector.lazyInstance(9, AssetsModule.UL_id._UL__ULSEP_com_oculus_appmanager_assets_AssetStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAssetList(packageName, apkUpdateInfo.baseVersion);
        HashMap hashMap2 = new HashMap();
        for (AssetInfo assetInfo : assetList) {
            if (((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isAssetInstalled(packageName, assetInfo.name)) {
                hashMap2.put(assetInfo.name, assetInfo);
            }
        }
        HashSet<String> hashSet = new HashSet(hashMap2.keySet());
        hashSet.removeAll(hashMap.keySet());
        hashSet.removeAll(persistentAssetNames);
        hashMap.keySet().retainAll(hashMap2.keySet());
        BLog.d(TAG, "Found %d assets that need to be backed up prior to install", Integer.valueOf(hashMap.size()));
        HashMap hashMap3 = new HashMap();
        try {
            for (ApkUpdateInfo apkUpdateInfo4 : hashMap.values()) {
                String finalAssetPath = InstallerFileUtils.getFinalAssetPath(packageName, apkUpdateInfo4.getExtras().getAssetRequiredFilename());
                String assetBackupPath = ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAssetBackupPath(apkUpdateInfo4);
                hashMap3.put(assetBackupPath, finalAssetPath);
                apkUpdateInfo4.edit().putInstallBackupFilePath(assetBackupPath).save();
                move(finalAssetPath, assetBackupPath);
            }
            BLog.d(TAG, "All preparation complete, now installing obb(%d) and assets(%d)", Integer.valueOf(updateInfoCollection.obb == null ? 0 : 1), Integer.valueOf(updateInfoCollection.assets.size()));
            try {
                for (ApkUpdateInfo apkUpdateInfo5 : updateInfoCollection.assets) {
                    move(apkUpdateInfo5.getExtras().getPreparedInstallFilePath(), ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getFinalAssetPath(apkUpdateInfo5));
                    apkUpdateInfo5.edit().putInstallationDurationMs(getDuration(apkUpdateInfo.getExtras().getInstallationStartTimeMs())).save();
                }
                if (updateInfoCollection.obb != null) {
                    move(updateInfoCollection.obb.getExtras().getPreparedInstallFilePath(), ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getFinalObbPath(updateInfoCollection.obb));
                    updateInfoCollection.obb.edit().putInstallationDurationMs(getDuration(apkUpdateInfo.getExtras().getInstallationStartTimeMs())).save();
                }
                for (String str : hashMap3.keySet()) {
                    ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).removeFileWithReason(str, "Removing backup");
                }
                ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).removeFileWithReason(((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getFinalObbPath(packageName, apkUpdateInfo.baseVersion), "Removing old install's obb file");
                for (String str2 : hashSet) {
                    if (((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isAssetInstalled(packageName, str2)) {
                        ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).removeFileWithReason(InstallerFileUtils.getFinalAssetPath(packageName, str2), StringFormatUtil.formatStrLocaleSafe("Removing old asset file: %s", str2));
                    }
                }
                ((InstallerCleanUpHelper) FbInjector.lazyInstance(10, InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCleanUpHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cleanUpStorage(apkUpdateInfo);
                ((InstallerAnalytics) FbInjector.lazyInstance(3, AnalyticsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportInstallFunnelInstallSuccess(apkUpdateInfo);
                BLog.i(TAG, "Completed install for %s. obb: %s. asset count: %s", packageName, updateInfoCollection.obb != null ? "present" : "not present", Integer.valueOf(updateInfoCollection.assets.size()));
                for (ApkUpdateInfo apkUpdateInfo6 : arrayList2) {
                    apkUpdateInfo6.edit().putState(ApkUpdateInfoContract.UpdateState.SUCCESS, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now()).save();
                }
                ((InstallerAnalytics) FbInjector.lazyInstance(3, AnalyticsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportInstallSuccess(updateInfoCollection);
                ((InstallerEventEmitter) FbInjector.lazyInstance(11, InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerEventEmitter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifyInstallSucceeded(apkUpdateInfo);
            } catch (IOException e) {
                ((InfoUtils) FbInjector.lazyInstance(2, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failQuiet(apkUpdateInfo, ErrorCategories.INSTALL_FILE_MOVE, "failed to perform obb/asset install", InstallerResultError.IO_ERROR, e);
                deletePreparedFiles(arrayList, "removing prepared file after failed install");
                try {
                    for (Map.Entry entry : hashMap3.entrySet()) {
                        move((String) entry.getKey(), (String) entry.getValue());
                    }
                } catch (IOException e2) {
                    ((IErrorReporter) FbInjector.lazyInstance(8, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.INSTALL_RECOVERY_FAILURE, "Failed to rename/move file during install recovery");
                    ((InstallerAnalytics) FbInjector.lazyInstance(3, AnalyticsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportInstallRecoveryFailed(apkUpdateInfo, e2);
                }
            }
        } catch (IOException e3) {
            ((InfoUtils) FbInjector.lazyInstance(2, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failQuiet(apkUpdateInfo, ErrorCategories.INSTALL_FILE_MOVE, "failed to backup assets", InstallerResultError.IO_ERROR, e3);
            deletePreparedFiles(arrayList, "Removing prepared file after failed backup");
            try {
                for (Map.Entry entry2 : hashMap3.entrySet()) {
                    move((String) entry2.getKey(), (String) entry2.getValue());
                }
            } catch (IOException e4) {
                ((IErrorReporter) FbInjector.lazyInstance(8, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.INSTALL_RECOVERY_FAILURE, "Failed to rename/move file during install recovery");
                ((InstallerAnalytics) FbInjector.lazyInstance(3, AnalyticsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportInstallRecoveryFailed(apkUpdateInfo, e4);
            }
        }
    }

    private void move(String str, String str2) throws IOException {
        BLog.d(TAG, "Moving %s -> %s", str, str2);
        ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).move(str, str2);
    }

    private void deletePreparedFiles(List<ApkUpdateInfo> list, String str) {
        for (ApkUpdateInfo apkUpdateInfo : list) {
            ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).removeFileWithReason(apkUpdateInfo.getExtras().getPreparedInstallFilePath(), str);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e6 A[SYNTHETIC, Splitter:B:36:0x00e6] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f0  */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doInstallVerifiedApk(com.oculus.appmanager.info.ApkUpdateInfo r15) throws java.lang.Throwable {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.appmanager.installer.service.InstallerServiceInstallHelper.doInstallVerifiedApk(com.oculus.appmanager.info.ApkUpdateInfo):void");
    }

    private void onApkInstallFailed(ApkUpdateInfo apkUpdateInfo, @Nullable Throwable th, @Nullable String str, int i) {
        if (TextUtils.isEmpty(str) || !str.startsWith(NO_CERTIFICATES_ERROR) || i != 4 || ((InstallerRetryHelper) FbInjector.lazyInstance(7, InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerRetryHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).maybeRetryForFullVersion(apkUpdateInfo) == -1) {
            BLog.e(TAG, th, "Installation failed for %s. Message: %s", apkUpdateInfo, str);
            String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("%s (%d)", str, Integer.valueOf(i));
            apkUpdateInfo.edit().putInstallationDurationMs(getDuration(apkUpdateInfo.getExtras().getInstallationStartTimeMs())).putErrorCode(i).save();
            ((InfoUtils) FbInjector.lazyInstance(2, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failQuiet(apkUpdateInfo, ErrorCategories.INSTALL_APK_FAILED, formatStrLocaleSafe, InstallerResultError.INSTALLER_ERROR, th);
            return;
        }
        ((IErrorReporter) FbInjector.lazyInstance(8, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.INSTALL_APK_FAILED, "INSTALL_PARSE_FAILED_NO_CERTIFICATES caught and retried");
    }

    private long getDuration(long j) {
        if (j != -1) {
            return ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now() - j;
        }
        return -1;
    }
}