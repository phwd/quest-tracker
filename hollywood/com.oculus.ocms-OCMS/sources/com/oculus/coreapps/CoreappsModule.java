package com.oculus.coreapps;

import com.facebook.common.identifiers.IdentifiersModule;
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
import com.oculus.appmanager.installer.events.EventsModule;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.device.noop_subscriber.NoOpDeviceAuthTokenSubscriberModule;
import com.oculus.auth.util.UtilModule;
import com.oculus.base.app.AppInfoModule;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.packagescache.PackagesCacheModule;
import com.oculus.config.ConfigModule;
import com.oculus.debug.DebugModule;
import com.oculus.executors.ExecutorsModule;
import com.oculus.horizon.api.ApiModule;
import com.oculus.horizon.logging.LoggingModule;
import com.oculus.http.common.HttpModule;
import com.oculus.libraryapi.OVRLibraryModule;

@InjectorModule
public class CoreappsModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_coreapps_CoreAppManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_coreapps_CoreAppManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(CoreAppManager.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForCoreappsModule {
        AutoGeneratedBindingsForCoreappsModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(IdentifiersModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(InfoModule.class);
                binder.require(EventsModule.class);
                binder.require(CredentialsModule.class);
                binder.require(NoOpDeviceAuthTokenSubscriberModule.class);
                binder.require(UtilModule.class);
                binder.require(AppInfoModule.class);
                binder.require(AppInitModule.class);
                binder.require(PackagesCacheModule.class);
                binder.require(ConfigModule.class);
                binder.require(DebugModule.class);
                binder.require(ExecutorsModule.class);
                binder.require(ApiModule.class);
                binder.require(LoggingModule.class);
                binder.require(HttpModule.class);
                binder.require(OVRLibraryModule.class);
                binder.bind(CoreAppManager.class).toProvider(new CoreAppManagerAutoProvider()).in(ApplicationScoped.class);
            }
        }
    }
}