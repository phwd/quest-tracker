package com.oculus.appmanager.installer.service;

import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.installer.service.InstallerServiceModule;
import com.oculus.downloader.DownloaderModule;
import com.oculus.downloader.OculusDownloader;
import com.oculus.downloader.extras.contract.DownloadExtrasKeys;
import com.oculus.downloader.model.DownloadInfo;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_downloader_OculusDownloader_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID"})
public class InstallerCancelHelper {
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private final InstallerServiceDownloadHelper mDownloadHelper;
    @Inject
    @Eager
    private final InfoUtils mInfoUtils;
    @Inject
    @Eager
    private final OculusDownloader mOculusDownloader;

    @AutoGeneratedAccessMethod
    public static final InstallerCancelHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCancelHelper_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (InstallerCancelHelper) UL.factorymap.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCancelHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final InstallerCancelHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCancelHelper_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new InstallerCancelHelper(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_service_InstallerCancelHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCancelHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_service_InstallerCancelHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCancelHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public InstallerCancelHelper(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        this.mInfoUtils = InfoUtils._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_ACCESS_METHOD(injectorLike);
        this.mDownloadHelper = InstallerServiceDownloadHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_ACCESS_METHOD(injectorLike);
        this.mOculusDownloader = DownloaderModule._UL__ULSEP_com_oculus_downloader_OculusDownloader_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public boolean cancelUpdateIfPossible(ApkUpdateInfo apkUpdateInfo) {
        if (!apkUpdateInfo.getState().isCancelable()) {
            return false;
        }
        DownloadInfo query = this.mOculusDownloader.query(apkUpdateInfo.getExtras().getDownloadId());
        if (this.mDownloadHelper.cancelAnyRunningDownloads(apkUpdateInfo) && query != null) {
            long j = -1;
            long j2 = query.extras.getLong(DownloadExtrasKeys.KEY_DOWNLOAD_SCHEDULED_TIME_MS, -1);
            long j3 = query.extras.getLong(DownloadExtrasKeys.KEY_DOWNLOAD_START_TIME_MS, -1);
            long elapsedRealtime = ((Clock) FbInjector.lazyInstance(0, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).elapsedRealtime();
            long seconds = (j2 == -1 || j3 == -1) ? -1 : TimeUnit.MILLISECONDS.toSeconds(j3 - j2);
            if (j3 != -1) {
                j = TimeUnit.MILLISECONDS.toSeconds(elapsedRealtime - j3);
            }
            apkUpdateInfo.edit().putDownloadPendingDurationSec(seconds).putDownloadRunningDurationSec(j).putDownloadScheduledTime(j2).putDownloadStartTime(j3).save();
        }
        apkUpdateInfo.edit().putState(ApkUpdateInfoContract.UpdateState.CANCELED, ((Clock) FbInjector.lazyInstance(0, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now()).save();
        return true;
    }

    public void onUpdateCanceled(ApkUpdateInfo apkUpdateInfo) {
        if (apkUpdateInfo.getExtras().getCancelledBy() == -1) {
            propagateCancel(this.mInfoUtils.getRootUpdate(apkUpdateInfo), apkUpdateInfo);
        }
    }

    private void propagateCancel(ApkUpdateInfo apkUpdateInfo, ApkUpdateInfo apkUpdateInfo2) {
        this.mDownloadHelper.cancelAnyRunningDownloads(apkUpdateInfo);
        ApkUpdateInfo.UpdateExtrasEditor edit = apkUpdateInfo.edit();
        if (apkUpdateInfo.getState() != ApkUpdateInfoContract.UpdateState.CANCELED) {
            edit.putState(ApkUpdateInfoContract.UpdateState.CANCELED, ((Clock) FbInjector.lazyInstance(0, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now());
        }
        if (apkUpdateInfo != apkUpdateInfo2) {
            edit.putCanceledBy(apkUpdateInfo2.id);
        }
        edit.putFailureCode(InstallerResultError.USER_CANCELLED.code);
        edit.save();
        for (ApkUpdateInfo apkUpdateInfo3 : this.mInfoUtils.getDependencies(apkUpdateInfo)) {
            if (apkUpdateInfo3.getState().isCancelable()) {
                propagateCancel(apkUpdateInfo3, apkUpdateInfo2);
            }
        }
    }
}