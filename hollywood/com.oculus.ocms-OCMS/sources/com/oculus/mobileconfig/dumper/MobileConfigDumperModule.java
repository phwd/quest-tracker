package com.oculus.mobileconfig.dumper;

import com.facebook.fab.dumper.base.DumperBaseModule;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AddToMultiBind;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.facebook.mobileconfig.interfaces.MobileConfigInterfacesModule;
import com.facebook.mobileconfig.metadata.MobileConfigParamsMapModule;
import com.facebook.mobileconfig.override.OverrideModule;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.config.interfaces.ConfigInterfacesModule;
import com.oculus.mobileconfig.init.MobileConfigInitModule;

@InjectorModule
public abstract class MobileConfigDumperModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigDumperPlugin.class)));
    }

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract DumperPlugin addConfigUpdaterDumperPlugin(MobileConfigDumperPlugin mobileConfigDumperPlugin);

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForMobileConfigDumperModule {
        AutoGeneratedBindingsForMobileConfigDumperModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(DumperBaseModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(MobileConfigFactoryModule.class);
                binder.require(MobileConfigFactoryImplModule.class);
                binder.require(MobileConfigInterfacesModule.class);
                binder.require(MobileConfigParamsMapModule.class);
                binder.require(OverrideModule.class);
                binder.require(ConfigInterfacesModule.class);
                binder.require(MobileConfigInitModule.class);
                binder.bindMulti(DumperPlugin.class).add(MobileConfigDumperPlugin.class);
                binder.bind(MobileConfigDumperPlugin.class).toProvider(new MobileConfigDumperPluginAutoProvider());
            }
        }
    }
}
