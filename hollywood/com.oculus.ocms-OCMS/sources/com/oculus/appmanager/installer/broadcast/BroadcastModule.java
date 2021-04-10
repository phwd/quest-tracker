package com.oculus.appmanager.installer.broadcast;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.common.init.AppInitModule;

@InjectorModule
public class BroadcastModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_broadcast_AssetBroadcastDispatch_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_installer_broadcast_AssetBroadcastDispatch_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AssetBroadcastDispatch.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForBroadcastModule {
        AutoGeneratedBindingsForBroadcastModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.require(InfoModule.class);
                binder.require(AppInitModule.class);
                binder.bind(AssetBroadcastDispatch.class).toProvider(new AssetBroadcastDispatchAutoProvider()).in(ApplicationScoped.class);
            }
        }
    }
}
