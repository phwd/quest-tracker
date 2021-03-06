package com.oculus.appmanager.installer.service;

import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoListener;
import com.oculus.appmanager.installer.contract.InstallerServiceContract;
import com.oculus.appmanager.installer.service.InstallerServiceModule;
import java.util.Set;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerConsistencyHelper_ULSEP_BINDING_ID"})
public class UpdateStateListener implements ApkUpdateInfoListener {
    @Inject
    @Eager
    private final InstallerConsistencyHelper mInstallerConsistencyHelper;
    @Inject
    @Eager
    private final InstallerServiceContract mInstallerServiceContract;

    @Override // com.oculus.appmanager.info.ApkUpdateInfoListener
    public void onCreated(ApkUpdateInfo apkUpdateInfo, ApkUpdateInfoListener.CreationType creationType) {
    }

    @Override // com.oculus.appmanager.info.ApkUpdateInfoListener
    public void onDeleted(ApkUpdateInfo apkUpdateInfo) {
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_service_UpdateStateListener_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_UpdateStateListener_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final UpdateStateListener _UL__ULSEP_com_oculus_appmanager_installer_service_UpdateStateListener_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (UpdateStateListener) UL.factorymap.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_UpdateStateListener_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final UpdateStateListener _UL__ULSEP_com_oculus_appmanager_installer_service_UpdateStateListener_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new UpdateStateListener(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_service_UpdateStateListener_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_UpdateStateListener_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public UpdateStateListener(InjectorLike injectorLike) {
        this.mInstallerServiceContract = InstallerServiceContract._UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_ACCESS_METHOD(injectorLike);
        this.mInstallerConsistencyHelper = InstallerConsistencyHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerConsistencyHelper_ULSEP_ACCESS_METHOD(injectorLike);
    }

    @Override // com.oculus.appmanager.info.ApkUpdateInfoListener
    public void onChanged(ApkUpdateInfo apkUpdateInfo, Set<String> set, ApkUpdateInfo.ApkUpdateExtras apkUpdateExtras) {
        for (String str : set) {
            if ("state".equals(str)) {
                this.mInstallerConsistencyHelper.onApkUpdateInfoStateChanged(apkUpdateInfo);
                switch (apkUpdateInfo.getState()) {
                    case DOWNLOADED:
                        onStateChangedToDownloaded(apkUpdateInfo);
                        continue;
                    case VERIFIED:
                        onStateChangedToVerified(apkUpdateInfo);
                        continue;
                    case SUCCESS:
                        onStateChangedToSuccess(apkUpdateInfo);
                        continue;
                    case FAILED:
                        onStateChangedToFailed(apkUpdateInfo);
                        continue;
                    case CANCELED:
                        onStateChangedToCanceled(apkUpdateInfo);
                        continue;
                    case RETRIED:
                        onStateChangedToRetried(apkUpdateInfo);
                        continue;
                }
            }
        }
    }

    private void onStateChangedToDownloaded(ApkUpdateInfo apkUpdateInfo) {
        this.mInstallerServiceContract.onDownloadComplete(apkUpdateInfo, false);
    }

    private void onStateChangedToVerified(ApkUpdateInfo apkUpdateInfo) {
        this.mInstallerServiceContract.onVerificationComplete(apkUpdateInfo);
    }

    private void onStateChangedToSuccess(ApkUpdateInfo apkUpdateInfo) {
        this.mInstallerServiceContract.onUpdateSuccessful(apkUpdateInfo);
    }

    private void onStateChangedToFailed(ApkUpdateInfo apkUpdateInfo) {
        this.mInstallerServiceContract.onUpdateFailed(apkUpdateInfo);
    }

    private void onStateChangedToCanceled(ApkUpdateInfo apkUpdateInfo) {
        this.mInstallerServiceContract.onUpdateCancelled(apkUpdateInfo);
    }

    private void onStateChangedToRetried(ApkUpdateInfo apkUpdateInfo) {
        this.mInstallerServiceContract.onUpdateRetried(apkUpdateInfo);
    }
}
