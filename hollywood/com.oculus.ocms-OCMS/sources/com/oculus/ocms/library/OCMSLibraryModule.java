package com.oculus.ocms.library;

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
import com.oculus.appmanager.assets.AssetsModule;
import com.oculus.appmanager.info.ApkUpdateInfoListener;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.installer.common.CommonModule;
import com.oculus.appmanager.installer.events.EventsModule;
import com.oculus.appmanager.installer.service.util.UtilModule;
import com.oculus.appmanager.uninstaller.tasks.TasksModule;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.init.INeedInit;
import com.oculus.content.ContentModule;
import com.oculus.downloader.progress.DownloaderProgressModule;
import com.oculus.executors.ExecutorsModule;
import com.oculus.installer.InstallerModule;
import com.oculus.library.database.DatabaseModule;
import com.oculus.library.refresher.RefresherModule;
import com.oculus.library.security.SecurityModule;
import com.oculus.library.utils.UtilsModule;
import com.oculus.library.utils.app.AppConverterUtilsModule;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.multiuser.MultiuserModule;
import com.oculus.ocms.library.provider.LibraryDownloadListener;
import com.oculus.ocms.library.provider.LibraryDownloadListenerAutoProvider;
import com.oculus.ocms.library.provider.LibraryProvider;
import com.oculus.ocms.library.provider.LibraryProviderAutoProvider;
import com.oculus.ocms.library.provider.PublicLibraryProvider;
import com.oculus.ocms.library.provider.PublicLibraryProviderAutoProvider;
import com.oculus.ocms.library.provider.SharedAppsHelper;
import com.oculus.ocms.library.provider.SharedAppsHelperAutoProvider;
import com.oculus.ocms.library.receiver.ReceiverModule;
import com.oculus.ocms.library.service.ServiceModule;
import com.oculus.security.basecomponent.BasecomponentModule;
import com.oculus.time.TimeModule;
import com.oculus.userserver.api.inject.UserServerInjectorModule;
import com.oculus.util.device.DeviceModule;

@InjectorModule
public abstract class OCMSLibraryModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(EntitlementsInstallerEventListener.class)));
        public static final int _UL__ULSEP_com_oculus_ocms_library_OVRLibraryInstallerEventListener_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_ocms_library_OVRLibraryInstallerEventListener_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(OVRLibraryInstallerEventListener.class)));
        public static final int _UL__ULSEP_com_oculus_ocms_library_provider_LibraryDownloadListener_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_ocms_library_provider_LibraryDownloadListener_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LibraryDownloadListener.class)));
        public static final int _UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(SharedAppsHelper.class)));
    }

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract ApkUpdateInfoListener provideApkUpdateInfoListener(EntitlementsInstallerEventListener entitlementsInstallerEventListener);

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract INeedInit provideInstallerEventListener(EntitlementsInstallerEventListener entitlementsInstallerEventListener);

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract INeedInit provideListener(OVRLibraryInstallerEventListener oVRLibraryInstallerEventListener);

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract INeedInit provideListener(LibraryDownloadListener libraryDownloadListener);

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForOCMSLibraryModule {
        AutoGeneratedBindingsForOCMSLibraryModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.require(MobileConfigFactoryModule.class);
                binder.require(MobileConfigInterfacesModule.class);
                binder.require(AssetsModule.class);
                binder.require(InfoModule.class);
                binder.require(CommonModule.class);
                binder.require(EventsModule.class);
                binder.require(UtilModule.class);
                binder.require(com.oculus.appmanager.uninstaller.events.EventsModule.class);
                binder.require(TasksModule.class);
                binder.require(CredentialsModule.class);
                binder.require(AppInitModule.class);
                binder.require(ContentModule.class);
                binder.require(DownloaderProgressModule.class);
                binder.require(ExecutorsModule.class);
                binder.require(InstallerModule.class);
                binder.require(DatabaseModule.class);
                binder.require(RefresherModule.class);
                binder.require(SecurityModule.class);
                binder.require(UtilsModule.class);
                binder.require(AppConverterUtilsModule.class);
                binder.require(OVRLibraryModule.class);
                binder.require(MultiuserModule.class);
                binder.require(ReceiverModule.class);
                binder.require(ServiceModule.class);
                binder.require(BasecomponentModule.class);
                binder.require(TimeModule.class);
                binder.require(UserServerInjectorModule.class);
                binder.require(DeviceModule.class);
                binder.bindMulti(INeedInit.class).add(LibraryDownloadListener.class);
                binder.bindMulti(INeedInit.class).add(OVRLibraryInstallerEventListener.class);
                binder.bindMulti(INeedInit.class).add(EntitlementsInstallerEventListener.class);
                binder.bindMulti(ApkUpdateInfoListener.class).add(EntitlementsInstallerEventListener.class);
                binder.bind(EntitlementsInstallerEventListener.class).toProvider(new EntitlementsInstallerEventListenerAutoProvider()).in(ApplicationScoped.class);
                binder.bind(OVRLibraryInstallerEventListener.class).toProvider(new OVRLibraryInstallerEventListenerAutoProvider()).in(ApplicationScoped.class);
                binder.bind(LibraryDownloadListener.class).toProvider(new LibraryDownloadListenerAutoProvider());
                binder.bind(SharedAppsHelper.class).toProvider(new SharedAppsHelperAutoProvider());
                binder.bindComponent(LibraryProvider.class).toProvider(new LibraryProviderAutoProvider());
                binder.bindComponent(PublicLibraryProvider.class).toProvider(new PublicLibraryProviderAutoProvider());
            }
        }
    }
}
