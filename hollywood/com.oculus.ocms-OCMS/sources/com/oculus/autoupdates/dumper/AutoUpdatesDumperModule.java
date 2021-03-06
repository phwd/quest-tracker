package com.oculus.autoupdates.dumper;

import com.facebook.fab.dumper.base.DumperBaseModule;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AddToMultiBind;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.autoupdates.AutoUpdatesModule;

@InjectorModule
public abstract class AutoUpdatesDumperModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_autoupdates_dumper_AutoUpdatesDumperPlugin_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_autoupdates_dumper_AutoUpdatesDumperPlugin_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AutoUpdatesDumperPlugin.class)));
    }

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract DumperPlugin addAutoUpdatesDumperPlugin(AutoUpdatesDumperPlugin autoUpdatesDumperPlugin);

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForAutoUpdatesDumperModule {
        AutoGeneratedBindingsForAutoUpdatesDumperModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(DumperBaseModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(AutoUpdatesModule.class);
                binder.bindMulti(DumperPlugin.class).add(AutoUpdatesDumperPlugin.class);
                binder.bind(AutoUpdatesDumperPlugin.class).toProvider(new AutoUpdatesDumperPluginAutoProvider());
            }
        }
    }
}
