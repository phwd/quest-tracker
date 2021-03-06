package com.oculus.appmanager.installer.dumper;

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
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.installer.service.InstallerServiceModule;
import com.oculus.common.init.AppInitModule;
import com.oculus.time.TimeModule;

@InjectorModule
public abstract class InstallerDumperModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(InstallerServiceDumper.class)));
    }

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract DumperPlugin addAutoUpdatesDumperPlugin(InstallerServiceDumper installerServiceDumper);

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForInstallerDumperModule {
        AutoGeneratedBindingsForInstallerDumperModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(DumperBaseModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(InfoModule.class);
                binder.require(InstallerServiceModule.class);
                binder.require(AppInitModule.class);
                binder.require(TimeModule.class);
                binder.bindMulti(DumperPlugin.class).add(InstallerServiceDumper.class);
                binder.bind(InstallerServiceDumper.class).toProvider(new InstallerServiceDumperAutoProvider());
            }
        }
    }
}
