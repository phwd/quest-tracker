package com.oculus.downloader.progress;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import javax.inject.Provider;

@InjectorModule
public abstract class DownloaderProgressModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DownloadProgressTracker.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_downloader_progress_DownloadProgressTracker_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final DownloadProgressTracker _UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (DownloadProgressTracker) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_downloader_progress_DownloadProgressTracker_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForDownloaderProgressModule {
        AutoGeneratedBindingsForDownloaderProgressModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.assertBindingInstalled(DownloadProgressTracker.class);
                binder.require(BundledAndroidModule.class);
            }
        }
    }
}
