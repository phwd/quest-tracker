package com.oculus.appmanager.installer.notification;

import com.facebook.common.android.AndroidModule;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AddToMultiBind;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.interfaces.MobileConfigInterfacesModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.appmanager.downloader.progress.OuculusDownloadProgressTrackerModule;
import com.oculus.appmanager.info.ApkUpdateInfoListener;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.installer.events.EventsModule;
import com.oculus.appmanager.installer.notification.contract.ContractModule;
import com.oculus.appmanager.installer.service.InstallerServiceModule;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.init.INeedInit;
import com.oculus.downloader.progress.DownloaderProgressModule;
import com.oculus.executors.ExecutorsModule;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.time.TimeModule;
import com.oculus.util.device.DeviceModule;
import com.oculus.util.inject.UtilModule;
import com.oculus.util.thread.ThreadModule;

@InjectorModule
public abstract class InstallerNotificationsModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_InstallerNotificationManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_installer_notification_InstallerNotificationManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(InstallerNotificationManager.class)));
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationDownloadListener_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationDownloadListener_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(NotificationDownloadListener.class)));
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEligibility_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEligibility_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(NotificationEligibility.class)));
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEventListener_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEventListener_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(NotificationEventListener.class)));
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationUpdateInfoListener_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationUpdateInfoListener_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(NotificationUpdateInfoListener.class)));
    }

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract ApkUpdateInfoListener addUpdateInfoListener(NotificationUpdateInfoListener notificationUpdateInfoListener);

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract INeedInit addUpdateInfoListener(NotificationEventListener notificationEventListener, NotificationDownloadListener notificationDownloadListener);

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForInstallerNotificationsModule {
        AutoGeneratedBindingsForInstallerNotificationsModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(AndroidModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(MobileConfigFactoryModule.class);
                binder.require(MobileConfigInterfacesModule.class);
                binder.require(com.oculus.android.AndroidModule.class);
                binder.require(OuculusDownloadProgressTrackerModule.class);
                binder.require(InfoModule.class);
                binder.require(EventsModule.class);
                binder.require(ContractModule.class);
                binder.require(InstallerServiceModule.class);
                binder.require(com.oculus.appmanager.uninstaller.events.EventsModule.class);
                binder.require(AppInitModule.class);
                binder.require(DownloaderProgressModule.class);
                binder.require(ExecutorsModule.class);
                binder.require(OVRLibraryModule.class);
                binder.require(TimeModule.class);
                binder.require(DeviceModule.class);
                binder.require(UtilModule.class);
                binder.require(ThreadModule.class);
                binder.bindMulti(INeedInit.class).add(NotificationDownloadListener.class);
                binder.bindMulti(INeedInit.class).add(NotificationEventListener.class);
                binder.bindMulti(ApkUpdateInfoListener.class).add(NotificationUpdateInfoListener.class);
                binder.bind(InstallerNotificationManager.class).toProvider(new InstallerNotificationManagerAutoProvider()).in(ApplicationScoped.class);
                binder.bind(NotificationDownloadListener.class).toProvider(new NotificationDownloadListenerAutoProvider());
                binder.bind(NotificationEligibility.class).toProvider(new NotificationEligibilityAutoProvider());
                binder.bind(NotificationEventListener.class).toProvider(new NotificationEventListenerAutoProvider());
                binder.bind(NotificationUpdateInfoListener.class).toProvider(new NotificationUpdateInfoListenerAutoProvider());
                binder.bindComponent(InstallerNotificationService.class).toProvider(new InstallerNotificationServiceAutoProvider());
            }
        }
    }
}
