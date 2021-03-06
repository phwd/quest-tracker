package com.oculus.downloader.extras;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;

@InjectorModule
public class ExtrasModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_downloader_extras_DownloadExtras_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_downloader_extras_DownloadExtras_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DownloadExtras.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForExtrasModule {
        AutoGeneratedBindingsForExtrasModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.bind(DownloadExtras.class).toProvider(new DownloadExtrasAutoProvider()).in(ApplicationScoped.class);
            }
        }
    }
}
