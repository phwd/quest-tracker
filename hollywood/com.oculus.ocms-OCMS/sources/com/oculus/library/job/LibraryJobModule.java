package com.oculus.library.job;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AddToMultiBind;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorModule;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.interfaces.MobileConfigInterfacesModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.init.INeedInit;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.executors.ExecutorsModule;
import com.oculus.libraryapi.OVRLibraryModule;

@InjectorModule
public abstract class LibraryJobModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_library_job_LibraryJobInit_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_library_job_LibraryJobInit_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LibraryJobInit.class)));
        public static final int _UL__ULSEP_com_oculus_library_job_LibraryJobScheduler_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_library_job_LibraryJobScheduler_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LibraryJobScheduler.class)));
    }

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract INeedInit provideINeedInit(LibraryJobInit libraryJobInit);

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForLibraryJobModule {
        AutoGeneratedBindingsForLibraryJobModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.require(MobileConfigFactoryModule.class);
                binder.require(MobileConfigInterfacesModule.class);
                binder.require(AppInitModule.class);
                binder.require(InterfacesModule.class);
                binder.require(ExecutorsModule.class);
                binder.require(OVRLibraryModule.class);
                binder.bindMulti(INeedInit.class).add(LibraryJobInit.class);
                binder.bind(LibraryJobInit.class).toProvider(new LibraryJobInitAutoProvider());
                binder.bind(LibraryJobScheduler.class).toProvider(new LibraryJobSchedulerAutoProvider()).in(ApplicationScoped.class);
                binder.bindComponent(LibraryJobService.class).toProvider(new LibraryJobServiceAutoProvider());
            }
        }
    }
}
