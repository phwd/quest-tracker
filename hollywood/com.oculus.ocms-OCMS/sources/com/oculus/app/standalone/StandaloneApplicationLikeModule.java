package com.oculus.app.standalone;

import com.facebook.common.android.AndroidModule;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.interfaces.MobileConfigInterfacesModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.base.app.AppInfoModule;
import com.oculus.base.app.ApplicationLike;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.init.impl.ImplModule;
import com.oculus.debug.DebugModule;
import com.oculus.errorreporting.ErrorReportingModule;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import javax.inject.Provider;

@InjectorModule
public class StandaloneApplicationLikeModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_app_standalone_StandaloneApplicationLike_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_app_standalone_StandaloneApplicationLike_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(StandaloneApplicationLike.class)));
        public static final int _UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(ApplicationLike.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_base_app_ApplicationLike_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_base_app_ApplicationLike_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_BINDING_ID, injectorLike);
    }

    @ProviderMethod
    static ApplicationLike provideApplicationLike(StandaloneApplicationLike standaloneApplicationLike) {
        return standaloneApplicationLike;
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForStandaloneApplicationLikeModule {
        AutoGeneratedBindingsForStandaloneApplicationLikeModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(AndroidModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(MobileConfigFactoryModule.class);
                binder.require(MobileConfigInterfacesModule.class);
                binder.require(com.oculus.android.AndroidModule.class);
                binder.require(CredentialsModule.class);
                binder.require(AppInfoModule.class);
                binder.require(AppInitModule.class);
                binder.require(ImplModule.class);
                binder.require(DebugModule.class);
                binder.require(ErrorReportingModule.class);
                binder.require(InterfacesModule.class);
                binder.bind(StandaloneApplicationLike.class).toProvider(new StandaloneApplicationLikeAutoProvider());
                binder.bind(ApplicationLike.class).toProvider(new ApplicationLikeMethodAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final ApplicationLike _UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ApplicationLike) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ApplicationLike _UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideApplicationLike(StandaloneApplicationLike._UL__ULSEP_com_oculus_app_standalone_StandaloneApplicationLike_ULSEP_ACCESS_METHOD(injectorLike));
    }
}