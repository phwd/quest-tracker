package com.oculus.appmanager.downloader.progress;

import com.facebook.common.android.AndroidModule;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.appmanager.downloader.OculusDownloaderModule;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.installer.broadcast.BroadcastModule;
import com.oculus.appmanager.installer.events.EventsModule;
import com.oculus.common.init.AppInitModule;
import com.oculus.downloader.DownloaderModule;
import com.oculus.downloader.contract.ContractModule;
import com.oculus.downloader.extras.ExtrasModule;
import com.oculus.downloader.progress.DownloadProgressTracker;
import com.oculus.downloader.progress.DownloaderProgressModule;
import com.oculus.errorreporting.ErrorReportingModule;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.time.TimeModule;
import com.oculus.util.thread.ThreadModule;
import javax.inject.Provider;

@InjectorModule
public class OuculusDownloadProgressTrackerModule extends AbstractLibraryModule {
    private static volatile DownloadProgressTracker _UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_INSTANCE;

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_appmanager_downloader_progress_OculusDownloadProgressTracker_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_downloader_progress_OculusDownloadProgressTracker_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(OculusDownloadProgressTracker.class)));
        public static final int _UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DownloadProgressTracker.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_downloader_progress_DownloadProgressTracker_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID, injectorLike);
    }

    @ApplicationScoped(enableScopeValidation = false)
    @ProviderMethod
    static DownloadProgressTracker provideProgressTracker(OculusDownloadProgressTracker oculusDownloadProgressTracker) {
        return oculusDownloadProgressTracker;
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForOuculusDownloadProgressTrackerModule {
        AutoGeneratedBindingsForOuculusDownloadProgressTrackerModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(AndroidModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(OculusDownloaderModule.class);
                binder.require(InfoModule.class);
                binder.require(BroadcastModule.class);
                binder.require(EventsModule.class);
                binder.require(AppInitModule.class);
                binder.require(DownloaderModule.class);
                binder.require(ContractModule.class);
                binder.require(ExtrasModule.class);
                binder.require(DownloaderProgressModule.class);
                binder.require(ErrorReportingModule.class);
                binder.require(InterfacesModule.class);
                binder.require(UtilsModule.class);
                binder.require(TimeModule.class);
                binder.require(ThreadModule.class);
                binder.bind(OculusDownloadProgressTracker.class).toProvider(new OculusDownloadProgressTrackerAutoProvider()).in(ApplicationScoped.class);
                binder.bind(DownloadProgressTracker.class).toProvider(new DownloadProgressTrackerMethodAutoProvider()).in(ApplicationScoped.class);
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_downloader_progress_DownloadProgressTracker_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final DownloadProgressTracker _UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (DownloadProgressTracker) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final DownloadProgressTracker _UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_INSTANCE == null) {
            synchronized (DownloadProgressTracker.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_INSTANCE = provideProgressTracker(OculusDownloadProgressTracker._UL__ULSEP_com_oculus_appmanager_downloader_progress_OculusDownloadProgressTracker_ULSEP_ACCESS_METHOD(injectorLike.getApplicationInjector()));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_INSTANCE;
    }
}
