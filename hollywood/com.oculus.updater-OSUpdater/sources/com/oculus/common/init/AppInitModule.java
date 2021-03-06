package com.oculus.common.init;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UltralightLazy;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import java.util.Set;

@InjectorModule
public abstract class AppInitModule extends AbstractLibraryModule {
    private static volatile AppInitLock _UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_INSTANCE;

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AppInitLock.class)));
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(new TypeLiteral<Set<INeedInit>>() {
            /* class com.oculus.common.init.AppInitModule.UL_id.AnonymousClass2 */
        })));
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(new TypeLiteral<Set<INeedInit>>() {
            /* class com.oculus.common.init.AppInitModule.UL_id.AnonymousClass1 */
        }, NeedsHighPriorityInit.class)));
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(new TypeLiteral<Set<INeedInit>>() {
            /* class com.oculus.common.init.AppInitModule.UL_id.AnonymousClass3 */
        }, NeedsInitInEnterpriseMode.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final AppInitLock _UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (AppInitLock) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final AppInitLock _UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_INSTANCE == null) {
            synchronized (AppInitLock.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_INSTANCE = provideAppInitLock();
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_INSTANCE;
    }

    @ApplicationScoped
    @ProviderMethod
    public static AppInitLock provideAppInitLock() {
        return new AppInitLock();
    }
}
