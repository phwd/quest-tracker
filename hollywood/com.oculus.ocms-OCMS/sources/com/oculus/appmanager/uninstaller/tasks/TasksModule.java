package com.oculus.appmanager.uninstaller.tasks;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.appmanager.uninstaller.events.EventsModule;
import com.oculus.appmanager.uninstaller.receivers.UninstallBroadcastReceiver;
import com.oculus.appmanager.uninstaller.receivers.UninstallBroadcastReceiverAutoProvider;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.packagescache.PackagesCacheModule;
import com.oculus.executors.ExecutorsModule;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.time.TimeModule;
import com.oculus.util.device.DeviceModule;

@InjectorModule
public class TasksModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_appmanager_uninstaller_tasks_UninstallerAsyncTaskProvider_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_appmanager_uninstaller_tasks_UninstallerAsyncTaskProvider_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(UninstallerAsyncTaskProvider.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForTasksModule {
        AutoGeneratedBindingsForTasksModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.require(EventsModule.class);
                binder.require(AppInitModule.class);
                binder.require(PackagesCacheModule.class);
                binder.require(ExecutorsModule.class);
                binder.require(UtilsModule.class);
                binder.require(TimeModule.class);
                binder.require(DeviceModule.class);
                binder.bindComponent(UninstallBroadcastReceiver.class).toProvider(new UninstallBroadcastReceiverAutoProvider());
                binder.bindAssistedProvider(UninstallerAsyncTaskProvider.class);
            }
        }
    }
}
