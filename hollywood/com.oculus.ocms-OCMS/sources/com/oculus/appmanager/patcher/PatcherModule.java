package com.oculus.appmanager.patcher;

import com.facebook.common.android.AndroidModule;
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
public class PatcherModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Patcher.class)));
        public static final int _UL__ULSEP_com_oculus_appmanager_patcher_RsyncNativeLibrary_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_patcher_RsyncNativeLibrary_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(RsyncNativeLibrary.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForPatcherModule {
        AutoGeneratedBindingsForPatcherModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(AndroidModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(com.oculus.android.AndroidModule.class);
                binder.bind(Patcher.class).toProvider(new PatcherAutoProvider()).in(ApplicationScoped.class);
                binder.bind(RsyncNativeLibrary.class).toProvider(new RsyncNativeLibraryAutoProvider()).in(ApplicationScoped.class);
            }
        }
    }
}
