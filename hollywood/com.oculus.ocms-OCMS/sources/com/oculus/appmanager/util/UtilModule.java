package com.oculus.appmanager.util;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.appmanager.assets.AssetsModule;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.installer.common.CommonModule;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.packagescache.PackagesCacheModule;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.library.database.DatabaseModule;
import com.oculus.util.device.DeviceModule;

@InjectorModule
public class UtilModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_appmanager_util_FileOps_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_util_FileOps_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(FileOps.class)));
        public static final int _UL__ULSEP_com_oculus_appmanager_util_InstallationDiskSpaceUtil_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_util_InstallationDiskSpaceUtil_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(InstallationDiskSpaceUtil.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForUtilModule {
        AutoGeneratedBindingsForUtilModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.require(AssetsModule.class);
                binder.require(InfoModule.class);
                binder.require(CommonModule.class);
                binder.require(AppInitModule.class);
                binder.require(PackagesCacheModule.class);
                binder.require(InterfacesModule.class);
                binder.require(DatabaseModule.class);
                binder.require(DeviceModule.class);
                binder.bind(FileOps.class).toProvider(new FileOpsAutoProvider());
                binder.bind(InstallationDiskSpaceUtil.class).toProvider(new InstallationDiskSpaceUtilAutoProvider());
            }
        }
    }
}
