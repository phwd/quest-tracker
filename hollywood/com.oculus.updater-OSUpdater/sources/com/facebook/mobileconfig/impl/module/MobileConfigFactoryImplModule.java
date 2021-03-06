package com.facebook.mobileconfig.impl.module;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UltralightProvider;
import com.facebook.mobileconfig.factory.module.SessionlessMC;
import com.facebook.mobileconfig.impl.MobileConfigDebugUtil;
import com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder;
import com.facebook.mobileconfig.impl.MobileConfigValueUtil;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

@InjectorModule
@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigFactoryImplModule extends AbstractLibraryModule {
    private static volatile MobileConfigManagerSingletonHolder _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_INSTANCE;
    private static volatile MobileConfigManagerSingletonHolder _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE;
    private static final Object _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_LOCK = new Object();

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigDebugUtil_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigDebugUtil_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigDebugUtil.class)));
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigManagerSingletonHolder.class)));
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigManagerSingletonHolder.class, (Class<? extends Annotation>) SessionlessMC.class)));
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigValueUtil_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigValueUtil_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigValueUtil.class)));
    }

    @AutoGeneratedAccessMethod
    public static final MobileConfigManagerSingletonHolder _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (MobileConfigManagerSingletonHolder) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final MobileConfigManagerSingletonHolder _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_INSTANCE == null) {
            synchronized (MobileConfigManagerSingletonHolder.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_INSTANCE = provideMobileConfigManagerSingletonHolder();
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final MobileConfigManagerSingletonHolder _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (MobileConfigManagerSingletonHolder) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final MobileConfigManagerSingletonHolder _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_LOCK) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE = provideSessionlessMobileConfigManagerSingletonHolder();
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID, injectorLike);
    }

    @ApplicationScoped
    @ProviderMethod
    static MobileConfigManagerSingletonHolder provideMobileConfigManagerSingletonHolder() {
        return new MobileConfigManagerSingletonHolder();
    }

    @SessionlessMC
    @ApplicationScoped
    @ProviderMethod
    static MobileConfigManagerSingletonHolder provideSessionlessMobileConfigManagerSingletonHolder() {
        return new MobileConfigManagerSingletonHolder();
    }
}
