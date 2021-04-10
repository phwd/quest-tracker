package com.oculus.library.sync;

import com.facebook.common.android.AndroidModule;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.interfaces.MobileConfigInterfacesModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.core.interceptor.InterceptorModule;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.util.device.DeviceModule;

@InjectorModule
public class SyncModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_library_sync_LibrarySyncHelper_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_library_sync_LibrarySyncHelper_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LibrarySyncHelper.class)));
        public static final int _UL__ULSEP_com_oculus_library_sync_LibrarySyncMethods_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_library_sync_LibrarySyncMethods_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LibrarySyncMethods.class)));
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForSyncModule {
        AutoGeneratedBindingsForSyncModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(AndroidModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(MobileConfigFactoryModule.class);
                binder.require(MobileConfigInterfacesModule.class);
                binder.require(com.oculus.android.AndroidModule.class);
                binder.require(HttpModule.class);
                binder.require(ApiModule.class);
                binder.require(EndpointModule.class);
                binder.require(InterceptorModule.class);
                binder.require(UserAgentModule.class);
                binder.require(OVRLibraryModule.class);
                binder.require(UtilsModule.class);
                binder.require(DeviceModule.class);
                binder.bind(LibrarySyncHelper.class).toProvider(new LibrarySyncHelperAutoProvider());
                binder.bind(LibrarySyncMethods.class).toProvider(new LibrarySyncMethodsAutoProvider());
            }
        }
    }
}
