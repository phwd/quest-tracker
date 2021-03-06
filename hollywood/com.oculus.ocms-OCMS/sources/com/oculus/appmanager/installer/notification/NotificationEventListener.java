package com.oculus.appmanager.installer.notification;

import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.installer.events.DownloadResponse;
import com.oculus.appmanager.installer.events.InstallResponse;
import com.oculus.appmanager.installer.events.InstallerEventBus;
import com.oculus.appmanager.installer.notification.InstallerNotificationsModule;
import com.oculus.appmanager.installer.notification.contract.InstallerNotificationsContract;
import com.oculus.common.init.INeedInit;
import com.oculus.executors.OculusThreadExecutor;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_installer_notification_contract_InstallerNotificationsContract_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEligibility_ULSEP_BINDING_ID"})
public class NotificationEventListener implements INeedInit {
    @Inject
    @Eager
    private final InstallerNotificationsContract mInstallerNotificationsContract;
    @Inject
    @Eager
    private final NotificationEligibility mNotificationEligibility;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_notification_NotificationEventListener_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(InstallerNotificationsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEventListener_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final NotificationEventListener _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEventListener_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (NotificationEventListener) UL.factorymap.get(InstallerNotificationsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEventListener_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final NotificationEventListener _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEventListener_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new NotificationEventListener(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_notification_NotificationEventListener_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(InstallerNotificationsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEventListener_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public NotificationEventListener(InjectorLike injectorLike) {
        this.mInstallerNotificationsContract = InstallerNotificationsContract._UL__ULSEP_com_oculus_appmanager_installer_notification_contract_InstallerNotificationsContract_ULSEP_ACCESS_METHOD(injectorLike);
        this.mNotificationEligibility = NotificationEligibility._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEligibility_ULSEP_ACCESS_METHOD(injectorLike);
    }

    @Override // com.oculus.common.init.INeedInit
    public void init() {
        InstallerEventBus.getInstance().register(this);
    }

    @Subscribe
    public void onDownloadResponse(final DownloadResponse downloadResponse) {
        OculusThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.appmanager.installer.notification.NotificationEventListener.AnonymousClass1 */

            public void run() {
                NotificationEventListener.this.handleDownloadResponse(downloadResponse);
            }
        });
    }

    public void handleDownloadResponse(DownloadResponse downloadResponse) {
        if (this.mNotificationEligibility.shouldShowNotification(downloadResponse)) {
            if (downloadResponse.isSuccess) {
                this.mInstallerNotificationsContract.setInstalling(downloadResponse.installIdentifier, downloadResponse.isUpdate);
            } else {
                this.mInstallerNotificationsContract.setDownloadFailure(downloadResponse.installIdentifier, downloadResponse.isUpdate);
            }
        }
    }

    @Subscribe
    public void onInstallResponse(final InstallResponse installResponse) {
        OculusThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.appmanager.installer.notification.NotificationEventListener.AnonymousClass2 */

            public void run() {
                NotificationEventListener.this.handleInstallResponse(installResponse);
            }
        });
    }

    public void handleInstallResponse(InstallResponse installResponse) {
        if (this.mNotificationEligibility.shouldShowNotification(installResponse)) {
            if (installResponse.isSuccess) {
                this.mInstallerNotificationsContract.setCompleted(installResponse.installIdentifier, installResponse.isUpdate);
            } else {
                this.mInstallerNotificationsContract.setInstallFailure(installResponse.installIdentifier, installResponse.isUpdate);
            }
        }
    }
}
